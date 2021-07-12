package ua.org.code.personaldepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.org.code.personaldepartment.exception.model.FieldErrorModel;
import ua.org.code.personaldepartment.exception.status.RestBadRequestException;
import ua.org.code.personaldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personaldepartment.persistence.repository.CookerRepository;
import ua.org.code.personaldepartment.service.CookerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CookerServiceImpl implements CookerService {

    private final CookerRepository cookerRepository;

    @Autowired
    public CookerServiceImpl(CookerRepository cookerRepository) {
        this.cookerRepository = cookerRepository;
    }

    @Override
    public void create(CookerEntity entity) {
        if (cookerRepository.existsByEmail(entity.getEmail())) {
            throw new RestBadRequestException(
                    new FieldErrorModel("email",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Cooker with email " + entity.getEmail() + " is already present!"));
        }
        if (cookerRepository.existsByPhoneNumber(entity.getEmail())) {
            throw new RestBadRequestException(
                    new FieldErrorModel("phoneNumber",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Cooker with phone number " + entity.getPhoneNumber() + " is already present!"));
        }
        if (cookerRepository.existsByUsername(entity.getEmail())) {
            throw new RestBadRequestException(
                    new FieldErrorModel("username",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "Cooke " + entity.getPhoneNumber() + " is already present!"));
        }
        entity.setId(UUID.randomUUID());
        cookerRepository.save(entity);
    }

    @Override
    public void update(CookerEntity entity) {
        cookerRepository.save(entity);
    }

    @Override
    public CookerEntity findById(UUID id) {
        CookerEntity cooker = cookerRepository.findById(id).orElse(null);
        if (cooker == null) {
            log.error("No cooker with id {}", id);
            throw new RestBadRequestException(
                    new FieldErrorModel("id",
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            "No cooker with id " + id.toString()));
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
                            "No cooker with id " + id.toString()));
        }
        cookerRepository.deleteById(id);
        log.info("Successful deleting cooker with id {}", id);
    }
}
