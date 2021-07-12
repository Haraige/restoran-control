package ua.org.code.personaldepartment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.code.personaldepartment.persistence.repository.CookerRepository;
import ua.org.code.personaldepartment.persistence.repository.WaiterRepository;

@Service
public class PersonalServiceImpl {

    private final CookerRepository cookerRepository;
    private final WaiterRepository waiterRepository;

    @Autowired
    public PersonalServiceImpl(CookerRepository cookerRepository, WaiterRepository waiterRepository) {
        this.cookerRepository = cookerRepository;
        this.waiterRepository = waiterRepository;
    }

    public boolean existByUsername(String username) {
        return cookerRepository.existsByUsername(username) || waiterRepository.existsByUsername(username);
    }

}
