package ua.org.code.personneldepartment.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.org.code.personneldepartment.constants.KeycloakRolesConstants;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.persistence.repository.CookerRepository;
import ua.org.code.personneldepartment.service.CookerService;
import ua.org.code.personneldepartment.service.KeycloakService;
import ua.org.code.personneldepartment.service.PersonnelCheckForExistDataService;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class CookerServiceImpl implements CookerService {

    private final CookerRepository cookerRepository;
    private final PersonnelCheckForExistDataService checkForExistDataService;
    private final KeycloakService keycloakService;

    @Autowired
    public CookerServiceImpl(CookerRepository cookerRepository, PersonnelCheckForExistDataService checkForExistDataService, KeycloakService keycloakService) {
        this.cookerRepository = cookerRepository;
        this.checkForExistDataService = checkForExistDataService;
        this.keycloakService = keycloakService;
    }

    @Override
    public CookerEntity create(CookerEntity entity) {
        if (checkForExistDataService.existsByEmail(entity.getEmail())) {
            log.warn("Error while creating new cooker! Email {} has already been taken!", entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber())) {
            log.warn("Error while creating new cooker! phone number {} has already been taken!", entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername())) {
            log.warn("Error while creating new cooker! Username {} has already been taken!", entity.getUsername());
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker " + entity.getUsername() + " is already present!"));
        }

        String userPassword = RandomStringUtils.random(10, true, true);
        keycloakService.createUserWithRole(entity.getName(),
                entity.getSurname(),
                entity.getEmail(),
                entity.getUsername(),
                userPassword,
                KeycloakRolesConstants.COOKER_ROLE_REPRESENTATION
        );

        entity.setPassword(userPassword);
        entity.setKeycloakId(keycloakService.getUserIdByUsername(entity.getUsername()));

        log.info("Successful creating cooker with id {}", entity.getId());
        return cookerRepository.save(entity);
    }

    @Override
    public CookerEntity update(UUID id, CookerEntity entity) {
        CookerEntity updateEntity = findById(id);

        if (checkForExistDataService.existsByEmail(entity.getEmail()) &&
                !updateEntity.getEmail().equals(entity.getEmail())) {
            log.warn("Error while updating cooker with id {}! Email {} has already been taken!", id, entity.getEmail());
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with email " + entity.getEmail() + " is already present!"));
        }
        if (checkForExistDataService.existsByPhoneNumber(entity.getPhoneNumber()) &&
                !updateEntity.getPhoneNumber().equals(entity.getPhoneNumber())) {
            log.warn("Error while updating cooker with id {}! Phone number {} has already been taken!",
                    id, entity.getPhoneNumber());
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Worker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (checkForExistDataService.existsByUsername(entity.getUsername()) &&
                !updateEntity.getUsername().equals(entity.getUsername())) {
            log.warn("Error while updating cooker with id {}! Username {} has already been taken!",
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

        keycloakService.updateUserInfo(updateEntity.getKeycloakId(),
                updateEntity.getName(),
                updateEntity.getSurname(),
                updateEntity.getEmail(),
                updateEntity.getUsername());

        log.info("Successful updating cooker with id {}", entity.getId());

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
