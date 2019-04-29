package by.training.info_system.dao;

import by.training.info_system.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao extends Dao<Car> {
    Optional<List<Car>> findWithTerminatingMaintaining();
    Optional<List<Car>> findByBrand(final Character brand);
    Optional<List<Car>> findInRange(final Integer price1, final Integer price2);
}
