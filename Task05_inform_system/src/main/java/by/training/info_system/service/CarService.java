package by.training.info_system.service;

import by.training.info_system.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService extends Service {
    Optional<List<Car>> loadCars();
    Optional<Car> findById(long id);
}
