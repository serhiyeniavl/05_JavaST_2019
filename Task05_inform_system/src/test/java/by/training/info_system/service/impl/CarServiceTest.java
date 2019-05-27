package by.training.info_system.service.impl;

import by.training.info_system.dao.connection_pool.ConnectionPool;
import by.training.info_system.dao.impl.DaoManagerImpl;
import by.training.info_system.entity.Car;
import by.training.info_system.entity.data.CarInfo;
import by.training.info_system.service.CarService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarServiceTest {

    @Test
    public void testFindById() {
        ConnectionPool.getInstance();
        CarService service = new ServiceFactoryImpl(
                new DaoManagerImpl()).getService(CarService.class).orElseThrow();

        final long id = 2;
        Car car = Car.builder()
                .brandName("Volkswagen Polo")
                .rentPrice(Short.parseShort("8"))
                .imagePath("vwPolo.jpg")
                .carInfo(
                        CarInfo.builder()
                                .regNumber("4829HR-2")
                                .build()
                )
                .build();
        car.setId(id);

        Assert.assertEquals(service.findById(id).orElse(Car.builder().build()), car);

        ConnectionPool.getInstance().finalizePool();
    }
}