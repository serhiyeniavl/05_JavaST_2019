package by.training.info_system.dao.connection_pool;

import by.training.info_system.resource.DBConfigurationProvider;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class ConnectionPool {
    private String dbUrl = DBConfigurationProvider.getInstance().getProperty("db.url");
    private String dbDriver = DBConfigurationProvider.getInstance().getProperty("db.driver");
    private String dbUser = DBConfigurationProvider.getInstance().getProperty("db.manual_user");
    private String dbPassword = DBConfigurationProvider.getInstance().getProperty("db.manual_user_pass");

    private Lock lock;

    private BlockingQueue<PooledConnectionProxy> connectionQueue;
    private Set<PooledConnectionProxy> usedConnections;

    private Long connectionTimeout = 30L * 1000L; //30sec
    private Integer maxPoolSize = 25;
    private Integer initialSize = 5;

    private static ConnectionPool instance;


    private ConnectionPool() {
        connectionQueue = new LinkedBlockingDeque<>();
        usedConnections = new ConcurrentSkipListSet<>();

        lock = new ReentrantLock();

        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            log.error("Cannot load driver for DB.", e);
        }

        for (int i = 0; i < initialSize; i++) {
            try {
                connectionQueue.add(newConnection());
            } catch (SQLException e) {
                log.error("Error when try to create connection", e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connectionQueue.isEmpty()) {
            if (usedConnections.size() < maxPoolSize) {
                PooledConnectionProxy connection = null;
                try {
                    connection = newConnection();
                    usedConnections.add(connection);
                    return connection;
                } catch (SQLException e) {
                    log.error("Cannot create new connection", e);
                }
            } else {
                log.debug("Connection pool is full, waiting a free connection.");
            }
        }
        PooledConnectionProxy connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            log.error("Error when take connection from queue.", e);
            Thread.currentThread().interrupt();
        }
        usedConnections.add(connection);
        return connection;
    }

    void returnInPool(final PooledConnectionProxy connection) {
        lock.lock();
        try {
            if (connection.isValid(Integer.parseInt(connectionTimeout.toString()))) {
                connection.clearWarnings();
                usedConnections.remove(connection);
                connectionQueue.add(connection);
            }
        } catch (SQLException e) {
            log.warn("Cannot return connection in pool", e);
            finalizeConnection(connection);
        }
        lock.unlock();
    }

    private PooledConnectionProxy newConnection() throws SQLException {
        return new PooledConnectionProxy(DriverManager.getConnection(dbUrl, dbUser,  dbPassword));
    }

    private void finalizeConnection(final PooledConnectionProxy connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Cannot close connection after error with returning in pool", e);
        }
    }

    public void finalizePool() {
        usedConnections.addAll(connectionQueue);
        connectionQueue.clear();
        usedConnections.forEach(this::finalizeConnection);
        usedConnections.clear();
    }

    public void setConnectionTimeout(final Long sec) {
        this.connectionTimeout = sec * 1000L;
    }

    public void setMaxPoolSize(final Integer maxSize) {
        this.maxPoolSize = maxSize;
    }

    public void setInitialSize(final Integer pooSize) {
        this.initialSize = pooSize;
    }
}
