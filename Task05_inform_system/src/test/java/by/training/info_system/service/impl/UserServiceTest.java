package by.training.info_system.service.impl;

import by.training.info_system.dao.connection_pool.ConnectionPool;
import by.training.info_system.dao.impl.DaoManagerImpl;
import by.training.info_system.entity.Passport;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import by.training.info_system.service.UserService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UserServiceTest {

    private User customer1;
    private User customer2;
    private User manager;

    private UserService service;

    @BeforeClass
    public void init() {
        ConnectionPool.getInstance();
        customer1 = User.builder()
                .email("hac@mail.ru")
                .password("$2a$10$XLBHhzsZY6O6J4nmRJYimeuBkWRiZV0cdqeVahPdHFPwbqWpJEPmC")
                .role(Role.USER)
                .userData(
                        UserData.builder()
                                .fName("Kirill")
                                .lName("Petrov")
                                .address("Minsk, Kurchatova 5/53")
                                .passport(
                                        Passport.builder()
                                                .serie("HK")
                                                .number(4435674)
                                                .idNumber("5943856C451PD2")
                                                .issueDate(LocalDate.of(2014, 2, 21))
                                                .endDate(LocalDate.of(2024, 2, 21))
                                                .build()
                                )
                                .build()
                )
                .build();
        customer1.setId(4L);

        customer2 = User.builder()
                .email("marshal@gmail.com")
                .password("$2a$10$CruzOrrcTRJft0hKsJ/M5.X5Qpl1wg8SWfwsXvqkpjeLuqn1H.RGy")
                .role(Role.USER)
                .userData(
                        UserData.builder()
                                .fName("Andrey")
                                .lName("Svirid")
                                .address("Minsk, Dzerjinskogo 11")
                                .passport(
                                        Passport.builder()
                                                .serie("AB")
                                                .number(4959395)
                                                .idNumber("4851723C758PB3")
                                                .issueDate(LocalDate.of(2015, 8, 23))
                                                .endDate(LocalDate.of(2025, 8, 23))
                                                .build()
                                )
                                .build()
                )
                .build();
        customer2.setId(2L);

        manager = User.builder()
                .email("neadrd@gmail.com")
                .role(Role.MANAGER)
                .userData(
                        UserData.builder()
                                .fName("Dmitry")
                                .lName("Ivanov")
                                .address("Minsk, Kurchatova 8/20")
                                .passport(
                                        Passport.builder()
                                                .serie("AB")
                                                .number(5839392)
                                                .idNumber("5913484C354PD2")
                                                .issueDate(LocalDate.of(2016, 9, 23))
                                                .endDate(LocalDate.of(2026, 9, 23))
                                        .build()
                                )
                        .build()
                )
                .build();
        manager.setId(3L);
        service = new ServiceFactoryImpl(
                new DaoManagerImpl()).getService(UserService.class).orElseThrow();
    }

    @AfterClass
    public void finalizePool() {
        service = null;
        ConnectionPool.getInstance().finalizePool();
    }

    @Test
    public void testFindByEmail() {
        User actual = service.findByEmailFullInfo("hac@mail.ru").orElse(User.builder().build());
        Assert.assertEquals(actual, customer1);
    }

    @Test
    public void testFindById() {
        User actual = service.findById(2L).orElse(User.builder().build());
        Assert.assertEquals(actual, customer2);
    }


    @Test
    public void testCountUsers() {
        int users = service.countUsers();
        Assert.assertEquals(users, 4);
    }

    @Test
    public void testIsInBlackList() {
        boolean actual = service.isInBlackList(customer1);
        Assert.assertTrue(actual);
    }

    @Test
    public void testFindManagers() {
        List<User> actual = service.findManagers(1, 5).orElseGet(ArrayList::new);
        Assert.assertEquals(actual, Collections.singletonList(manager));
    }
}