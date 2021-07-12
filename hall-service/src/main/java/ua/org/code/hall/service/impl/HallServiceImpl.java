package ua.org.code.hall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.code.hall.entity.TableEntity;
import ua.org.code.hall.repository.HallRepository;
import ua.org.code.hall.service.HallService;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public void create(TableEntity tableEntity) {
        hallRepository.save(tableEntity);
    }

    @Override
    public void update(TableEntity tableEntity) {
        hallRepository.save(tableEntity);
    }

    @Override
    public TableEntity findById(Integer id) {
        return hallRepository.getById(id);
    }

    @Override
    public List<TableEntity> getAll() {
        return hallRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        TableEntity entity = findById(id);
        hallRepository.delete(entity);
    }
}
