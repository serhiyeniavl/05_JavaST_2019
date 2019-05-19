package by.training.info_system.service;

import by.training.info_system.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService extends Service {
    Integer addCar(Car car);
    Optional<List<Car>> loadCars();
    Optional<Car> findById(long id);
}
