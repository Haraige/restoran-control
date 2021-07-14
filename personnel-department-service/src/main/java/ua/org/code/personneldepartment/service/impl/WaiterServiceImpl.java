package ua.org.code.personneldepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;
import ua.org.code.personneldepartment.persistence.repository.WorkingDayRepository;
import ua.org.code.personneldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personneldepartment.service.PersonnelCheckForExistDataService;
import ua.org.code.personneldepartment.service.WaiterService;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WaiterServiceImpl implements WaiterService {

    private final WaiterRepository waiterRepository;
    private final WorkingDayRepository workingDayRepository;
    private final PersonnelCheckForExistDataService checkForExistDataService;

    @Autowired
    public WaiterServiceImpl(WaiterRepository waiterRepository,
                             WorkingDayRepository workingDayRepository,
                             PersonnelCheckForExistDataService checkForExistDataService) {
        this.waiterRepository = waiterRepository;
        this.workingDayRepository = workingDayRepository;
        this.checkForExistDataService = checkForExistDataService;
    }

    @Override
    public void create(WaiterEntity entity) {
        if (checkForExistDataService.existsByEmail(entity.getEmail())) {
            log.error("Error while creating new waiter! Email {} has already been taken!", entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getEmail())) {
            log.error("Error while creating new waiter! phone number {} has already been taken!", entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername())) {
            log.error("Error while creating new waiter! Username {} has already been taken!", entity.getUsername());
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with username " + entity.getUsername() + " is already present!"));
        }

        entity.setId(UUID.randomUUID());
        entity.setHiredAt(new Date());

        log.info("Successful creating waiter with id {}", entity.getId());
        waiterRepository.save(entity);
    }

    @Override
    public void update(UUID id, WaiterEntity entity) {
        WaiterEntity updateEntity = findById(id);

        if (checkForExistDataService.existsByEmail(entity.getEmail()) &&
                !updateEntity.getEmail().equals(entity.getEmail())) {
            log.error("Error while updating waiter with id {}! Email {} has already been taken!", id, entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber()) &&
                !updateEntity.getPhoneNumber().equals(entity.getPhoneNumber())) {
            log.error("Error while updating waiter with id {}! Phone number {} has already been taken!",
                    id, entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername()) &&
                !updateEntity.getUsername().equals(entity.getUsername())) {
            log.error("Error while updating waiter with id {}! Username {} has already been taken!",
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
        updateEntity.setPassword(entity.getPassword());
        waiterRepository.save(updateEntity);
    }

    @Override
    public WaiterEntity findById(UUID id) {
        WaiterEntity waiter = waiterRepository.findById(id).orElse(null);
        if (waiter == null) {
            log.error("Error while finding waiter!" +
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
            log.error("Error while deleting waiter!" +
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
    public void addWaiterWorkingDays(UUID id, List<DayOfWeek> daysOfWeek) {
        WaiterEntity waiterEntity = findById(id);
        List<WorkingDayEntity> workingDayEntities =
                daysOfWeek.
                        stream().
                        map(dayOfWeek -> new WorkingDayEntity(waiterEntity, dayOfWeek)).
                        collect(Collectors.toList());
        workingDayRepository.saveAll(workingDayEntities);
    }

    @Override
    public WaiterEntity findByUsername(String username) {
        WaiterEntity waiter = waiterRepository.findByUsername(username).orElse(null);
        if (waiter == null) {
            log.error("Error while getting waiter by username!" +
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
            log.error("Error while getting waiter by username!" +
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
            log.error("Error while getting waiter by phone number!" +
                    "No waiter with phone number {}", phoneNumber);
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with phone number " + phoneNumber));
        }
        return waiter;
    }

    @Override
    public List<WorkingDayEntity> getWorkingDays(UUID id) {
        if (!waiterRepository.existsById(id)) {
            log.error("Error while getting waiters' working days!" +
                    "No waiter with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No waiter with id " + id));
        }
        log.info("Successful delete waiter with id {}!", id);
        return workingDayRepository.findAllByWorkerId(id);
    }

}
