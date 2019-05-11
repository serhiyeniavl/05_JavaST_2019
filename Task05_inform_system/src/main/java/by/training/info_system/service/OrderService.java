package by.training.info_system.service;

import by.training.info_system.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service {
    Integer createNewOrder(Order order);
    boolean isActiveOrder(long carId, long userId);
    Optional<List<Order>> findUserOrders(long id);
    Optional<List<Order>> findAllOrders();
    Optional<Order> findOrderById(long id);
}
