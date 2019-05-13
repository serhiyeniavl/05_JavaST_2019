package by.training.info_system.service;

import by.training.info_system.entity.Order;
import by.training.info_system.entity.status.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service {
    Integer createNewOrder(Order order);
    Integer countOrders();
    Integer countOrders(long userId);
    boolean isActiveOrder(long userId);
    boolean updateOrderStatus(long id, OrderStatus status);
    boolean updateOrder(Order order);
    Optional<List<Order>> findUserOrders(long id, int page, int recordsPerPage);
    Optional<List<Order>> findAllOrders();
    Optional<List<Order>> findAllOrders(int page, int recordsPerPage);
    Optional<Order> findOrderById(long id);

}
