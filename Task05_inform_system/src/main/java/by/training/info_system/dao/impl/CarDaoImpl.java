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
    public Integer create(final Car entity) {
        String sql1 = "INSERT INTO Cars "
                + "(description, year_made, brand_name, rent_price, class_auto, "
                + "image_path) VALUES (?,?,?,?,?,?)";
        String sql2 = "INSERT  INTO Car_info "
                + "(car_id, vin_code, reg_number, run) VALUES (?,?,?,?)";

        PreparedStatement statement1 = createPreparedStatement(sql1,
                Statement.RETURN_GENERATED_KEYS);
        PreparedStatement statement2 = createPreparedStatement(sql2);
        try {
            statement1.setString(1, entity.getDescription());
            statement1.setShort(2, entity.getYearMade());
            statement1.setString(3, entity.getBrandName());
            statement1.setShort(4, entity.getRentPrice());
            statement1.setString(5, entity.getCarClass().toString());
            statement1.setString(6, entity.getImagePath());

            statement2.setString(2, entity.getCarInfo().getVinCode());
            statement2.setString(3, entity.getCarInfo().getRegNumber());
            statement2.setInt(4, entity.getCarInfo().getRun());
        } catch (SQLException e) {
            log.error("Cannot create prepared statement", e);
        }
        int carId = 0;
        ResultSet resultSet = null;
        try {
            int query = statement1.executeUpdate();
            if (query < 1) {
                throw new SQLException("Cannot insert into Cars table");
            }
            resultSet = statement1.getGeneratedKeys();
            if (resultSet.next()) {
                carId = resultSet.getInt(1);
            }
            statement2.setInt(1, carId);
            int query1 = statement2.executeUpdate();
            if (query1 < 1) {
                throw new SQLException("Cannot insert into Cars_info table");
            }
            return 1;
        } catch (SQLException e) {
            log.error("Error when trying to insert a row in Cars", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.error("Error when close result set", e);
                }
            }
            closePreparedStatement(statement1);
            closePreparedStatement(statement2);
        }
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
    public boolean update(final Car entity) {
        throw new UnsupportedOperationException(METHOD_DOESNT_SUPPORT);
    }

    @Override
    public boolean delete(final long id) {
        throw new UnsupportedOperationException(METHOD_DOESNT_SUPPORT);
    }

}
