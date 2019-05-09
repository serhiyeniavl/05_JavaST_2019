package by.training.info_system.service.impl;

import by.training.info_system.dao.OrderDao;
import by.training.info_system.entity.Order;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl extends AbstractService implements OrderService {
    @Override
    public boolean createNewOrder(final Order order) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        daoManager.autoCommit(false);
        daoManager.commit();
        boolean isCreated = dao.create(order);
        if (isCreated) {
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
}
