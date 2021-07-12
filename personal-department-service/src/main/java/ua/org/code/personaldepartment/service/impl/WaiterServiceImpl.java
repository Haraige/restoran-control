package ua.org.code.personaldepartment.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.code.personaldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personaldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personaldepartment.service.WaiterService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WaiterServiceImpl implements WaiterService {

    private final WaiterRepository waiterRepository;

    @Autowired
    public WaiterServiceImpl(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    @Override
    public void create(WaiterEntity waiterEntity) {

        waiterRepository.save(waiterEntity);
    }

    @Override
    public void update(WaiterEntity waiterEntity) {
         waiterRepository.save(waiterEntity);
    }

    @Override
    public WaiterEntity findById(UUID id) {
        return waiterRepository.getById(id);
    }

    @Override
    public List<WaiterEntity> getAll() {
        return waiterRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        WaiterEntity entity = waiterRepository.getById(id);
        waiterRepository.delete(entity);
    }
}
