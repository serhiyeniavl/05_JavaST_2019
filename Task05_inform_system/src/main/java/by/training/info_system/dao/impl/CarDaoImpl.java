package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.CarDao;
import by.training.info_system.entity.Car;

import java.util.List;
import java.util.Optional;

public class CarDaoImpl extends AbstractDao implements CarDao {
    @Override
    public Optional<List<Car>> findWithTerminatingMaintaining() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findByBrand(Character brand) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findInRange(Integer price1, Integer price2) {
        return Optional.empty();
    }

    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public Optional<Car> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> getAll() {
        return Optional.empty();
    }

    @Override
    public boolean update(Car entity) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

}
