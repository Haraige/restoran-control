package ua.org.code.personaldepartment.service;


import java.util.List;
import java.util.UUID;

public interface CRUDService <ENTITY> {
    void create(ENTITY entity);
    void update(ENTITY entity);
    ENTITY findById(UUID id);
    List<ENTITY> getAll();
    void delete(UUID id);
}
