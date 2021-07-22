package ua.org.code.hall.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ua.org.code.hall.exception.model.FieldErrorModel;
import ua.org.code.hall.exception.status.RestBadRequestException;
import ua.org.code.hall.exception.status.RestConflictException;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.peristence.repository.TableManageRepository;
import ua.org.code.hall.service.TableManageService;
import ua.org.code.hall.view.VO.WaiterBasicVO;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Log4j2
public class TableManageServiceImpl implements TableManageService {

    private final TableManageRepository tableManageRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public TableManageServiceImpl(TableManageRepository tableManageRepository, RestTemplate restTemplate) {
        this.tableManageRepository = tableManageRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public TableEntity create(TableEntity tableEntity) {
        TableEntity savedEntity = tableManageRepository.save(tableEntity);
        log.info("Successful creating table with id {}", savedEntity.getId());
        return savedEntity;
    }

    @Override
    public TableEntity update(Integer id, TableEntity tableEntity) {
        TableEntity updateEntity = findById(id);

        updateEntity.setCustomersCapacity(tableEntity.getCustomersCapacity());
        updateEntity.setFree(tableEntity.isFree());
        updateEntity.setWaiterId(tableEntity.getWaiterId());

        log.info("Successful updating table with id {}", id);
        return tableManageRepository.save(updateEntity);
    }

    @Override
    public TableEntity findById(Integer id) {
        TableEntity table = tableManageRepository.findById(id).orElse(null);

        if (table == null) {
            log.warn("No table with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No table with id " + id));

        }
        log.info("Successful getting table with id {}", id);
        return table;
    }

    @Override
    public List<TableEntity> getAll() {
        log.info("Successful getting all tables");
        return tableManageRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        TableEntity entity = findById(id);

        log.info("Successful deleting table with id {}", id);
        tableManageRepository.delete(entity);
    }

    @Override
    public void reserveTable(Integer id) {
        TableEntity table = findById(id);
        if (!table.isFree()) {
            log.warn("Error while reserving table. Table with id {} already reserved", id);
            throw new RestConflictException(
                    new FieldErrorModel("id",
                    HttpStatus.CONFLICT.getReasonPhrase(),
                    "Table with id {} already reserved", id));
        }
        log.info("Successful reserve table with id {}", id);
        tableManageRepository.setTableFreeById(id, false);
    }

    @Override
    public void freeTable(Integer id) {
        TableEntity table = findById(id);
        if (table.isFree()) {
            log.warn("Error while free table. Table with id {} already free", id);
            throw new RestConflictException(
                    new FieldErrorModel("id",
                            HttpStatus.CONFLICT.getReasonPhrase(),
                            "Table with id {} already free", id));
        }
        log.info("Successful free table with id {}", id);
        tableManageRepository.setTableFreeById(id, true);
    }

    @Override
    @Scheduled(cron = "0 5 0 * * *")
    @Transactional
    public void refreshAllWaitersTables() {
        List<WaiterBasicVO> currentDayWaiters = getCurrentDayWaiters();
        List<TableEntity> tables = getAll();

        if (currentDayWaiters.isEmpty()) {
            log.warn("No waiters for current day! Set waiters for {}", LocalDate.now().getDayOfWeek());
            tables.forEach(entity -> tableManageRepository.setTableWaiterById(entity.getId(), null));
            return;
        }
        int tablesPerWaiter = tables.size() / currentDayWaiters.size();
        int remainingTables = tables.size() % currentDayWaiters.size();

        int currentTableId = 0;
        for (int waiterCounter = 0; waiterCounter < currentDayWaiters.size(); waiterCounter++) {
            WaiterBasicVO waiter = currentDayWaiters.get(waiterCounter);
            for (int tableCounter = 0; tableCounter < tablesPerWaiter; tableCounter++, currentTableId++) {
                Integer tableId = tables.get(currentTableId).getId();
                tableManageRepository.setTableWaiterById(tableId, waiter.getId());
            }
            if (remainingTables < waiterCounter) {
                tableManageRepository.setTableWaiterById(tables.get(currentTableId).getId(), waiter.getId());
                currentTableId++;
            }
        }

        log.info("Successful refresh all waiter`s tables");
    }

    @Override
    public List<TableEntity> getAllFreeTablesFromDateToDate(Date from, Date to) {
        log.info("Successful getting all free tables from {} to {} date",
                DateTimeFormatter.ISO_DATE_TIME.format(from.toInstant()),
                DateTimeFormatter.ISO_DATE_TIME.format(to.toInstant()));
        return tableManageRepository.getTableEntitiesByFreeIsTrueFromAndTo(from, to);
    }

    private List<WaiterBasicVO> getCurrentDayWaiters() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .forEach(s -> System.out.println(s.getAuthority()));

        String bearerToken = jwt.getTokenValue();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        ResponseEntity<WaiterBasicVO[]> responseEntity = restTemplate.exchange(
                "http://PERSONNEL-DEPARTMENT-SERVICE/personnel-department/waiters/schedules/day/" + currentDay,
                HttpMethod.GET, httpEntity, WaiterBasicVO[].class);

        return List.of(Objects.requireNonNull(responseEntity.getBody()));
    }
}
