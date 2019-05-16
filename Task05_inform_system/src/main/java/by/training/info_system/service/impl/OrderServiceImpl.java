package by.training.info_system.service.impl;

import by.training.info_system.dao.OrderDao;
import by.training.info_system.entity.Order;
import by.training.info_system.entity.status.OrderStatus;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl extends AbstractService implements OrderService {
    @Override
    public Integer createNewOrder(final Order order) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        daoManager.autoCommit(false);
        daoManager.commit();
        int isCreated = dao.create(order);
        if (isCreated != 0) {
            daoManager.commit();
        } else {
            daoManager.rollback();
        }
        daoManager.autoCommit(true);
        return isCreated;
    }

    @Override
    public Optional<List<Order>> findAllOrders(final int page,
                                               final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.getAll(page, recordsPerPage);
    }

    @Override
    public Optional<Order> findOrderById(long id) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.get(id);
    }

    @Override
    public boolean isActiveOrder(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.isActive(userId);
    }

    @Override
    public Optional<List<Order>> findUserOrders(final long id,
                                                final int page,
                                                final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findOrders(id, page, recordsPerPage);
    }

    @Override
    public Optional<Order> findOrder(final long userId, final long orderId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findOrder(userId, orderId);
    }

    @Override
    public boolean updateOrderStatus(final long id, final OrderStatus status) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.update(id, status);
    }

    @Override
    public Optional<List<Order>> findAllOrders() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.getAll();
    }

    @Override
    public boolean updateOrder(final Order order) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.update(order);
    }

    @Override
    public Integer countOrders() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countOrders();
    }

    @Override
    public Integer countOrders(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countOrders(userId);
    }
}
