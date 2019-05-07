package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.CarDao;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.data.CarInfo;
import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
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
        String sql = "SELECT description, vin_code, year_made, brand_name, rent_price," +
                "class_auto, image_path FROM Cars JOIN Car_info Ci on Cars.id = Ci.car_id";
        Statement statement = createStatement();
        try(ResultSet resultSet = statement.executeQuery(sql)) {
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                CarInfo info = CarInfo.builder()
                        .description(resultSet.getString("description"))
                        .yearMade(resultSet.getShort("year_made"))
                        .build();
                Car car = Car.builder()
                        .vinCode(resultSet.getString("vin_code"))
                        .brandName(resultSet.getString("brand_name"))
                        .carClass(resultSet.getString("class_auto").charAt(0))
                        .rentPrice(resultSet.getShort("rent_price"))
                        .carInfo(info)
                        .imagePath(resultSet.getString("image_path"))
                        .build();
                cars.add(car);
            }
            return Optional.of(cars);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
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
