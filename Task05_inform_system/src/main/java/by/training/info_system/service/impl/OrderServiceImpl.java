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
    public Optional<List<Order>> findOverdue(final int page,
                                             final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findOverdue(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findOverdue(final long userId,
                                             final int page,
                                             final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findOverdue(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findNotConfirmed(final int page,
                                                  final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findNotConfirmed(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findNotConfirmed(final long userId,
                                                  final int page,
                                                  final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findNotConfirmed(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findAccepted(final int page,
                                              final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findAccepted(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findAccepted(final long userId,
                                              final int page,
                                              final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findAccepted(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findActive(final int page,
                                            final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findActive(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findActive(final long userId,
                                            final int page,
                                            final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findActive(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findDenied(final int page,
                                            final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findDenied(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findDenied(final long userId,
                                            final int page,
                                            final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findDenied(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findExtended(final int page,
                                              final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findExtended(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findExtended(final long userId,
                                              final int page,
                                              final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findExtended(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findCompleted(final int page,
                                               final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findCompleted(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findCompleted(final long userId,
                                               final int page,
                                               final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findCompleted(userId, page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findConfirmed(final int page,
                                               final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findConfirmed(page, recordsPerPage);
    }

    @Override
    public Optional<List<Order>> findConfirmed(final long userId,
                                               final int page,
                                               final int recordsPerPage) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.findConfirmed(userId, page, recordsPerPage);
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
    public Integer countOverdue() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countOverdue();
    }

    @Override
    public Integer countOverdue(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countOverdue(userId);
    }

    @Override
    public Integer countNotConfirmed() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countNotConfirmed();
    }

    @Override
    public Integer countNotConfirmed(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countNotConfirmed(userId);
    }

    @Override
    public Integer countAccepted() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countAccepted();
    }

    @Override
    public Integer countAccepted(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countAccepted(userId);
    }

    @Override
    public Integer countActive() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countActive();
    }

    @Override
    public Integer countActive(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countActive(userId);
    }

    @Override
    public Integer countDenied() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countDenied();
    }

    @Override
    public Integer countDenied(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countDenied(userId);
    }

    @Override
    public Integer countExtended() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countExtended();
    }

    @Override
    public Integer countExtended(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countExtended(userId);
    }

    @Override
    public Integer countCompleted() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countCompleted();
    }

    @Override
    public Integer countCompleted(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countCompleted(userId);
    }

    @Override
    public Integer countConfirmed() {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countConfirmed();
    }

    @Override
    public Integer countConfirmed(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countConfirmed(userId);
    }

    @Override
    public Integer countOrders(final long userId) {
        OrderDao dao = daoManager.createDao(OrderDao.class).orElseThrow();
        return dao.countOrders(userId);
    }
}
