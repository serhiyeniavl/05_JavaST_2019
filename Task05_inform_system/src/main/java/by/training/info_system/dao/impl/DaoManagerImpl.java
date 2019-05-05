package by.training.info_system.dao.impl;

import by.training.info_system.dao.*;
import by.training.info_system.dao.DaoManager;
import by.training.info_system.dao.connection_pool.ConnectionPool;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class DaoManagerImpl implements DaoManager {
    private static final Map<Class<? extends Dao<?>>, Class<? extends AbstractDao>> CLASSES = new ConcurrentHashMap<>();

    static {
        CLASSES.put(UserDao.class, UserDaoImpl.class);
        CLASSES.put(CarDao.class, CarDaoImpl.class);
        CLASSES.put(OrderDao.class, OrderDaoImpl.class);
    }

    private Connection connection;


    public DaoManagerImpl() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    @Override
    public <T extends Dao<?>> Optional<T> createDao(final Class<T> key) {
        Class<? extends AbstractDao> value = CLASSES.get(key);
        if  (value != null) {
            try {
                AbstractDao dao = value.getDeclaredConstructor(null).newInstance(null);
                dao.setConnection(connection);
                return Optional.of((T)dao);
            } catch (InstantiationException e) {
                log.error("Error when triy to create an instance of a class", e);
            } catch (IllegalAccessException e) {
                log.error("Impossible to create a new instance of class", e);
            } catch (InvocationTargetException e) {
                log.error("Invoked constructor threw an error: ", e);
            } catch (NoSuchMethodException e) {
                log.error("No such constructor", e);
            }
        }
        return Optional.empty();
    }

    @Override
    public void autoCommit(boolean commit) {
        try {
            connection.setAutoCommit(commit);
        } catch (SQLException e) {
            log.error("Cannot to set auto commit argument", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot commit", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("Cannot rollback", e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Error when try to close connection", e);
        }
    }
}
