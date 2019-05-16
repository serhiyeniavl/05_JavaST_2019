package by.training.info_system.dao;

import by.training.info_system.entity.Order;
import by.training.info_system.entity.status.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order> {
    Integer countOrders();
    Integer countOrders(long userId);
    boolean isActive(long userId);
    boolean update(long id, OrderStatus status);
    Optional<Order> findOrder(long userId, long orderId);
    Optional<List<Order>> readCurrentOrders();
    Optional<List<Order>> getAll(int page, int ordersPerPage);
    Optional<List<Order>> findOverdue();
    Optional<List<Order>> findOrders(long userId);
    Optional<List<Order>> findOrders(long userId, int page, int recordsPerPage);
}
