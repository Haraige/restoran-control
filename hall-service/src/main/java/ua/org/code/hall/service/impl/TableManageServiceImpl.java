package ua.org.code.hall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.peristence.repository.TableManageRepository;
import ua.org.code.hall.service.TableManageService;

import java.util.List;

@Service
public class TableManageServiceImpl implements TableManageService {

    private final TableManageRepository tableManageRepository;

    @Autowired
    public TableManageServiceImpl(TableManageRepository tableManageRepository) {
        this.tableManageRepository = tableManageRepository;
    }

    @Override
    public void create(TableEntity tableEntity) {
        tableManageRepository.save(tableEntity);
    }

    @Override
    public void update(TableEntity tableEntity) {
        tableManageRepository.save(tableEntity);
    }

    @Override
    public TableEntity findById(Integer id) {
        return tableManageRepository.getById(id);
    }

    @Override
    public List<TableEntity> getAll() {
        return tableManageRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        TableEntity entity = findById(id);
        tableManageRepository.delete(entity);
    }
}
