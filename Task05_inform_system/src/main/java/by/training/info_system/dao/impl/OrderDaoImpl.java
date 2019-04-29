package by.training.info_system.dao.impl;

import by.training.info_system.dao.AbstractDao;
import by.training.info_system.dao.OrderDao;
import by.training.info_system.entity.Order;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends AbstractDao implements OrderDao {
    @Override
    public Optional<List<Order>> readCurrentOrders() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> findOverdue() {
        return Optional.empty();
    }

    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public Optional<Order> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Order>> getAll() {
        return Optional.empty();
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(final long id) {
        return false;
    }

}
