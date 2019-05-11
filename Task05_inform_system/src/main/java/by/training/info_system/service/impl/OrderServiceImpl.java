package by.training.info_system.service.impl;

import by.training.info_system.dao.OrderDao;
import by.training.info_system.entity.Order;
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
    public Optional<List<Order>> findAllOrders() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.getAll();
    }

    @Override
    public Optional<Order> findOrderById(long id) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.get(id);
    }

    @Override
    public boolean isActiveOrder(final long carId, final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.isActive(carId, userId);
    }

    @Override
    public Optional<List<Order>> findUserOrders(final long id) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findOrders(id);
    }
}
