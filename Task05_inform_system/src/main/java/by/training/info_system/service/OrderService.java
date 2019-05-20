package by.training.info_system.service;

import by.training.info_system.entity.Order;
import by.training.info_system.entity.status.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService extends Service {
    Integer createNewOrder(Order order);
    Integer countOrders();
    Integer countOverdue();
    Integer countNotConfirmed();
    Integer countNotConfirmed(long userId);
    Integer countAccepted();
    Integer countAccepted(long userId);
    Integer countActive();
    Integer countActive(long userId);
    Integer countDenied();
    Integer countDenied(long userId);
    Integer countExtended();
    Integer countExtended(long userId);
    Integer countCompleted();
    Integer countCompleted(long userId);
    Integer countConfirmed();
    Integer countConfirmed(long userId);
    Integer countOverdue(long userId);
    Integer countOrders(long userId);
    boolean isActiveOrder(long userId);
    boolean updateOrderStatus(long id, OrderStatus status);
    boolean updateOrder(Order order);
    Optional<List<Order>> findUserOrders(long id, int page, int recordsPerPage);
    Optional<List<Order>> findActiveOrders();
    Optional<List<Order>> findAllOrders(int page, int recordsPerPage);
    Optional<List<Order>> findOverdue(int page, int recordsPerPage);
    Optional<List<Order>> findOverdue(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findNotConfirmed(int page, int recordsPerPage);
    Optional<List<Order>> findNotConfirmed(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findAccepted(int page, int recordsPerPage);
    Optional<List<Order>> findAccepted(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findActive(int page, int recordsPerPage);
    Optional<List<Order>> findActive(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findDenied(int page, int recordsPerPage);
    Optional<List<Order>> findDenied(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findExtended(int page, int recordsPerPage);
    Optional<List<Order>> findExtended(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findCompleted(int page, int recordsPerPage);
    Optional<List<Order>> findCompleted(long userId, int page, int recordsPerPage);
    Optional<List<Order>> findConfirmed(int page, int recordsPerPage);
    Optional<List<Order>> findConfirmed(long userId, int page, int recordsPerPage);
    Optional<Order> findOrderById(long id);
    Optional<Order> findOrder(long userId, long orderId);

}
