package by.training.info_system.service.impl;

import by.training.info_system.dao.CarDao;
import by.training.info_system.entity.Car;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.CarService;

import java.util.List;
import java.util.Optional;

public class CarServiceImpl extends AbstractService implements CarService {
    @Override
    public Optional<List<Car>> loadCars() {
        CarDao dao = daoManager.createDao(CarDao.class).orElseThrow();
        return dao.getAll();
    }

    @Override
    public Optional<Car> findById(final long id) {
        CarDao dao = daoManager.createDao(CarDao.class).orElseThrow();
        return dao.get(id);
    }
}
