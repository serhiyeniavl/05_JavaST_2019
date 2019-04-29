package by.training.info_system.dao;

import by.training.info_system.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Entity> {
    boolean create(T entity);
    Optional<T> get(long id);
    Optional<List<T>> getAll();
    boolean update(T entity);
    boolean delete(long id);
}
