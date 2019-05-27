package by.training.info_system.service.impl;

import by.training.info_system.dao.connection_pool.ConnectionPool;
import by.training.info_system.dao.impl.DaoManagerImpl;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.status.OrderStatus;
import by.training.info_system.service.OrderService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

public class OrderServiceTest {

    private OrderService service;

    @BeforeClass
    public void init() {
        ConnectionPool.getInstance();
        service = new ServiceFactoryImpl(
                new DaoManagerImpl()).getService(OrderService.class).orElseThrow();
    }

    @AfterClass
    public void finalizePool() {
        ConnectionPool.getInstance().finalizePool();
    }

    @Test
    public void testIsActiveOrder() {
        long userWithActiveOrderId = 2;
        Assert.assertTrue(service.isActiveOrder(userWithActiveOrderId));
    }

    @Test
    public void testFindExpiredOrders() {
        List<Order> actual = service.findOverdue(1, 5).orElseGet(ArrayList::new);
        Assert.assertEquals(actual, Collections.emptyList());
    }

    @Test
    public void testCountOrders() {
        Assert.assertEquals(service.countOrders(), Integer.valueOf(2));
    }
}