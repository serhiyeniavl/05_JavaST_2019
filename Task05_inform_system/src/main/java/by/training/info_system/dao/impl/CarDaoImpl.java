package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.CarDao;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.data.CarInfo;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
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
    public Optional<List<Car>> findByBrand(final Character brand) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> findInRange(final Integer price1, final Integer price2) {
        return Optional.empty();
    }

    @Override
    public Integer create(final Car entity) {
        return 0;
    }

    @Override
    public Optional<Car> get(final long id) {
        String sql = "SELECT id, brand_name, rent_price, image_path, reg_number "
                + "FROM Cars "
                + "JOIN Car_info Ci on Cars.id = Ci.car_id "
                + "WHERE Cars.id = ?";
        PreparedStatement statement = createPreparedStatement(sql);
        try {
            statement.setLong(1, id);
        } catch (SQLException e) {
            log.error("Cannot execute prepared statement", e);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return Optional.empty();
            }
            Car car = Car.builder()
                    .brandName(resultSet.getString("brand_name"))
                    .imagePath(resultSet.getString("image_path"))
                    .rentPrice(resultSet.getShort("rent_price"))
                    .carInfo(CarInfo.builder()
                            .regNumber(resultSet.getString("reg_number"))
                            .build())
                    .build();
            car.setId(resultSet.getLong("id"));
            return Optional.of(car);
        } catch (SQLException e) {
            log.error(RESULT_SET_ERROR, e);
        } finally {
            closeStatement(statement);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Car>> getAll() {
        String sql = "SELECT id, description, year_made, brand_name, rent_price,"
                + " class_auto, image_path FROM Cars";
        Statement statement = createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            List<Car> cars = new ArrayList<>();
            while (resultSet.next()) {
                Car car = Car.builder()
                        .brandName(resultSet.getString("brand_name"))
                        .description(resultSet.getString("description"))
                        .yearMade(resultSet.getShort("year_made"))
                        .carClass(resultSet.getString("class_auto").charAt(0))
                        .rentPrice(resultSet.getShort("rent_price"))
                        .imagePath(resultSet.getString("image_path"))
                        .build();
                car.setId(resultSet.getLong("id"));
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
