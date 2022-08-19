package com.revature.salad.daos;

import java.util.List;

// db container name postgres-db
// docker start postgres-db

public interface CrudeDAO<T> {

    void save(T obj);
    void update(T obj);
    void delete(String id);
    T getById(String id);
    List<T> getAll();

}
