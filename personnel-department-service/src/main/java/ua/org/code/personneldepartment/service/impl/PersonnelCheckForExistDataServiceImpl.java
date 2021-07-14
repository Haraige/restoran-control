package ua.org.code.personneldepartment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.code.personneldepartment.persistence.repository.CookerRepository;
import ua.org.code.personneldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personneldepartment.service.PersonnelCheckForExistDataService;

@Service
public class PersonnelCheckForExistDataServiceImpl implements PersonnelCheckForExistDataService {

    private final CookerRepository cookerRepository;
    private final WaiterRepository waiterRepository;

    @Autowired
    public PersonnelCheckForExistDataServiceImpl(CookerRepository cookerRepository, WaiterRepository waiterRepository) {
        this.cookerRepository = cookerRepository;
        this.waiterRepository = waiterRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return cookerRepository.existsByEmail(email) || waiterRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return cookerRepository.existsByPhoneNumber(phoneNumber) || waiterRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByUsername(String username) {
        return cookerRepository.existsByUsername(username) || waiterRepository.existsByUsername(username);
    }
}
