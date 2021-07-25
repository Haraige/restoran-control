package ua.org.code.personneldepartment.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;
import ua.org.code.personneldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personneldepartment.persistence.repository.WorkingDayRepository;
import ua.org.code.personneldepartment.service.PersonnelCheckForExistDataService;
import ua.org.code.personneldepartment.service.WaiterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class WaiterServiceImpl implements WaiterService {

    private final WaiterRepository waiterRepository;
    private final WorkingDayRepository workingDayRepository;
    private final PersonnelCheckForExistDataService checkForExistDataService;

    private final RestTemplate restTemplate;

    @Value("keycloak.admin-cli.client-secret")
    String adminCliSecret;

    @Autowired
    public WaiterServiceImpl(WaiterRepository waiterRepository,
                             WorkingDayRepository workingDayRepository,
                             PersonnelCheckForExistDataService checkForExistDataService, RestTemplate restTemplate) {
        this.waiterRepository = waiterRepository;
        this.workingDayRepository = workingDayRepository;
        this.checkForExistDataService = checkForExistDataService;
        this.restTemplate = restTemplate;
    }

    @Override
    public WaiterEntity create( WaiterEntity entity) {
        if (checkForExistDataService.existsByEmail(entity.getEmail())) {
            log.warn("Error while creating new waiter! Email {} has already been taken!", entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber())) {
            log.warn("Error while creating new waiter! phone number {} has already been taken!", entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername())) {
            log.warn("Error while creating new waiter! Username {} has already been taken!", entity.getUsername());
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with username " + entity.getUsername() + " is already present!"));
        }

        log.info("Successful creating waiter with id {}", entity.getId());
        return waiterRepository.save(entity);
    }

    @Override
    public WaiterEntity update(UUID id, WaiterEntity entity) {
        WaiterEntity updateEntity = findById(id);

        if (checkForExistDataService.existsByEmail(entity.getEmail()) &&
                !updateEntity.getEmail().equals(entity.getEmail())) {
            log.warn("Error while updating waiter with id {}! Email {} has already been taken!", id, entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber()) &&
                !updateEntity.getPhoneNumber().equals(entity.getPhoneNumber())) {
            log.warn("Error while updating waiter with id {}! Phone number {} has already been taken!",
                    id, entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername()) &&
                !updateEntity.getUsername().equals(entity.getUsername())) {
            log.warn("Error while updating waiter with id {}! Username {} has already been taken!",
                    id, entity.getUsername());
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with username " + entity.getUsername() + " is already present!"));
        }

        updateEntity.setName(entity.getName());
        updateEntity.setSurname(entity.getSurname());
        updateEntity.setEmail(entity.getEmail());
        updateEntity.setPhoneNumber(entity.getPhoneNumber());
        updateEntity.setDateOfBirth(entity.getDateOfBirth());
        updateEntity.setSalary(entity.getSalary());
        updateEntity.setUsername(entity.getUsername());
        return waiterRepository.save(updateEntity);
    }

    @Override
    public WaiterEntity findById(UUID id) {
        WaiterEntity waiter = waiterRepository.findById(id).orElse(null);
        if (waiter == null) {
            log.warn("Error while finding waiter!" +
                    "No waiter with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with id " + id));
        }
        log.info("Successful find waiter with id {}", id);
        return waiter;
    }

    @Override
    public List<WaiterEntity> getAll() {
        log.info("Successful find all waiters.");
        return waiterRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        WaiterEntity entity = waiterRepository.findById(id).orElse(null);
        if (entity == null) {
            log.warn("Error while deleting waiter!" +
                    "No waiter with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with id " + id));
        }

        log.info("Successful delete waiter with id {}", id);
        waiterRepository.delete(entity);
    }

    @Override
    public List<WorkingDayEntity> getAllWaitersWorkingDays() {
        log.info("Successful getting working days of all waiters");
        return workingDayRepository.findAllByTypeWaiter();
    }

    @Override
    public WaiterEntity findByUsername(String username) {
        WaiterEntity waiter = waiterRepository.findByUsername(username).orElse(null);
        if (waiter == null) {
            log.warn("Error while getting waiter by username!" +
                    "No waiter with username {}", username);
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with username " + username));
        }
        return waiter;
    }

    @Override
    public WaiterEntity findByEmail(String email) {
        WaiterEntity waiter = waiterRepository.findByEmail(email).orElse(null);
        if (waiter == null) {
            log.warn("Error while getting waiter by username!" +
                    "No waiter with email {}", email);
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with email " + email));
        }
        return waiter;
    }

    @Override
    public WaiterEntity findByPhoneNumber(String phoneNumber) {
        WaiterEntity waiter = waiterRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (waiter == null) {
            log.warn("Error while getting waiter by phone number!" +
                    "No waiter with phone number {}", phoneNumber);
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with phone number " + phoneNumber));
        }
        return waiter;
    }

    @Override
    public void addWaiterWorkingDays(UUID id, List<DayOfWeek> daysOfWeek) {
        WaiterEntity waiterEntity = findById(id);
        daysOfWeek.removeIf(dayOfWeek -> workingDayRepository.existsByDayOfWeekAndWorkerId(dayOfWeek.name(), id.toString()));

        List<WorkingDayEntity> workingDayEntities =
                daysOfWeek.
                        stream().
                        map(dayOfWeek -> new WorkingDayEntity(waiterEntity, dayOfWeek)).
                        collect(Collectors.toList());
        workingDayRepository.saveAll(workingDayEntities);
        log.info("Successful adding working days to waiter with id {}", id);
    }

    @Override
    public List<DayOfWeek> getWorkingDays(UUID id) {
        if (!waiterRepository.existsById(id)) {
            log.warn("Error while getting waiters' working days!" +
                    "No waiter with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with id " + id));
        }
        log.info("Successful returning waiters` working days with id {}!", id);
        return workingDayRepository.findAllByWorkerId(id.toString()).
                stream().
                map(WorkingDayEntity::getDayOfWeek).
                collect(Collectors.toList());
    }

    @Override
    public List<WaiterEntity> getAllWaitersByDayOfWeek(DayOfWeek dayOfWeek) {
        log.info("Successful returning waiters who are working on {}!", dayOfWeek);
        return waiterRepository.findAllByDayOfWeek(dayOfWeek.name());
    }

}
