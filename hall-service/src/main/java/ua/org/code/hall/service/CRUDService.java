package ua.org.code.hall.service;


import java.util.List;

public interface CRUDService<ENTITY, ID> {
    ENTITY create(ENTITY entity);
    ENTITY update(ID id, ENTITY entity);
    ENTITY findById(ID id);
    List<ENTITY> getAll();
    void delete(ID id);
}
