package ua.org.code.personneldepartment.service;

import java.util.List;
import java.util.UUID;

public interface CRUDService <ENTITY> {
    ENTITY create(ENTITY entity);
    ENTITY update(UUID id, ENTITY entity);
    ENTITY findById(UUID id);
    List<ENTITY> getAll();
    void delete(UUID id);
}
