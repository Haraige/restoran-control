package ua.org.code.hall.service;


import java.util.List;
import java.util.UUID;

public interface CRUDService<ENTITY> {
    void create(ENTITY entity);
    void update(ENTITY entity);
    ENTITY findById(Integer id);
    List<ENTITY> getAll();
    void delete(Integer id);
}
