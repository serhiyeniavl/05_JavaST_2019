package by.training.info_system.service;

import by.training.info_system.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service {
    boolean createNewOrder(Order order);
    Optional<List<Order>> findAllOrders();
}
