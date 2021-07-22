package ua.org.code.personneldepartment.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.persistence.repository.CookerRepository;
import ua.org.code.personneldepartment.service.CookerService;
import ua.org.code.personneldepartment.service.PersonnelCheckForExistDataService;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class CookerServiceImpl implements CookerService {

    private final CookerRepository cookerRepository;
    private final PersonnelCheckForExistDataService checkForExistDataService;

    @Autowired
    public CookerServiceImpl(CookerRepository cookerRepository, PersonnelCheckForExistDataService checkForExistDataService) {
        this.cookerRepository = cookerRepository;
        this.checkForExistDataService = checkForExistDataService;
    }

    @Override
    public CookerEntity create(CookerEntity entity) {
        if (checkForExistDataService.existsByEmail(entity.getEmail())) {
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber())) {
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername())) {
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker " + entity.getUsername() + " is already present!"));
        }

        return cookerRepository.save(entity);
    }

    @Override
    public CookerEntity update(UUID id, CookerEntity entity) {
        CookerEntity updateEntity = findById(id);

        if (checkForExistDataService.existsByEmail(entity.getEmail()) &&
                !updateEntity.getEmail().equals(entity.getEmail())) {
            log.error("Error while updating cooker with id {}! Email {} has already been taken!", id, entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber()) &&
                !updateEntity.getPhoneNumber().equals(entity.getPhoneNumber())) {
            log.error("Error while updating cooker with id {}! Phone number {} has already been taken!",
                    id, entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername()) &&
                !updateEntity.getUsername().equals(entity.getUsername())) {
            log.error("Error while updating cooker with id {}! Username {} has already been taken!",
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
        updateEntity.setCookerSpecializations(entity.getCookerSpecializations());
        updateEntity.setUsername(entity.getUsername());
        return cookerRepository.save(updateEntity);
    }

    @Override
    public CookerEntity findById(UUID id) {
        CookerEntity cooker = cookerRepository.findById(id).orElse(null);
        if (cooker == null) {
            log.error("No cooker with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No cooker with id " + id));
        }
        log.info("Successful find cooker with id {}", id);
        return cooker;
    }

    @Override
    public List<CookerEntity> getAll() {
        log.info("Successful get all cookers");
        return cookerRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        if (!cookerRepository.existsById(id)) {
            log.error("Error deleting cooker with id {}. No cooker with current id!", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No cooker with id " + id));
        }
        cookerRepository.deleteById(id);
        log.info("Successful deleting cooker with id {}", id);
    }
}
