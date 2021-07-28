package ua.org.code.hall.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.org.code.hall.exception.model.FieldErrorModel;
import ua.org.code.hall.exception.status.RestBadRequestException;
import ua.org.code.hall.exception.status.RestConflictException;
import ua.org.code.hall.peristence.entity.ReservationEntity;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.peristence.repository.ReservationRepository;
import ua.org.code.hall.peristence.repository.TableManageRepository;
import ua.org.code.hall.service.TableManageService;
import ua.org.code.hall.view.VO.WaiterBasicVO;
import ua.org.code.hall.view.dto.ReservationWithTableIdDto;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Log4j2
public class TableManageServiceImpl implements TableManageService {

    private final TableManageRepository tableManageRepository;
    private final ReservationRepository reservationRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public TableManageServiceImpl(TableManageRepository tableManageRepository, ReservationRepository reservationRepository, RestTemplate restTemplate) {
        this.tableManageRepository = tableManageRepository;
        this.reservationRepository = reservationRepository;
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
    public ReservationEntity reserveTable(ReservationWithTableIdDto reservation) {
        TableEntity table = findById(reservation.getTableId());

        if (getAllFreeTablesFromDateToDate(reservation.getDateFrom(), reservation.getDateTo())
                .stream()
                .noneMatch(entity -> entity.getId().equals(table.getId()))) {
            log.warn("Error while reserving table. Table with id {} already reserved on date {} to {}",
                    table.getId(), reservation.getDateFrom(), reservation.getDateTo());
            throw new RestConflictException(
                    new FieldErrorModel("id",
                            HttpStatus.CONFLICT.getReasonPhrase(),
                            "Table with id {} already reserved", table.getId()));
        }

        log.info("Successful reserve table with id {}", table.getId());

        ReservationEntity entity = new ReservationEntity(
                reservation.getName(),
                reservation.getSurname(),
                reservation.getPhoneNumber(),
                table,
                reservation.getDateFrom(),
                reservation.getDateTo()
        );

        return reservationRepository.save(entity);
    }

    @Override
    public void setCustomerFinishedTrue(UUID reservationId) {
        log.info("Successful set customer finished true to reservation with id");
        reservationRepository.setCustomerFinishedTrue(reservationId);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    @PostConstruct
    public void freeTables() {
        List<ReservationEntity> reservations = reservationRepository.findReservationEntitiesWhereCustomerNotCame(15);
        reservations.forEach(reservation -> reservationRepository.setCustomerAbsentTrue(reservation.getId()));
    }

    @Override
    @Scheduled(cron = "0 5 0 * * *")
    @Transactional
    public void refreshAllWaitersTables() {
        List<WaiterBasicVO> currentDayWaiters = this.getCurrentDayWaiters();
        List<TableEntity> tables = getAll();

        if (currentDayWaiters.size() == 0) {
            tables.forEach(entity -> tableManageRepository.setTableWaiterById(entity.getId(), null));
            log.warn("No waiters for current day! Set waiters for {}", LocalDate.now().getDayOfWeek());
            throw new RestConflictException (
                    new FieldErrorModel("",
                            HttpStatus.CONFLICT.getReasonPhrase(),
                            "No waiters for day" + LocalDate.now().getDayOfWeek())
            );
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
    public List<TableEntity> getAllFreeTablesFromDateToDate(LocalDateTime from, LocalDateTime to) {
        log.info("Successful getting all free tables from {} to {} date",
                DateTimeFormatter.ISO_DATE_TIME.format(from),
                DateTimeFormatter.ISO_DATE_TIME.format(to));
        return tableManageRepository.getTableEntitiesByFreeIsTrueFromAndTo(from, to);
    }

    @Override
    public List<WaiterBasicVO> getCurrentDayWaiters() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();

        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String bearerToken = jwt.getTokenValue();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);

        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
        ResponseEntity<WaiterBasicVO[]> responseEntity = restTemplate.exchange(
                "http://PERSONNEL-DEPARTMENT-SERVICE/personnel-department/waiters/schedules/day/" + currentDay,
                HttpMethod.GET, httpEntity, WaiterBasicVO[].class);

        if (responseEntity.getBody() == null) {
            log.warn("No waiters for day {}", currentDay);
            return new ArrayList<>();
        }
        return List.of(Objects.requireNonNull(responseEntity.getBody()));
    }
}
