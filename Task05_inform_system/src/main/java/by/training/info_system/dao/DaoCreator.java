package by.training.info_system.dao;

import java.util.Optional;

public interface DaoCreator {
    <T extends Dao<?>> Optional<T> createDao(final Class<T> key);

    void autoCommit(boolean commit);
    void commit();
    void rollback();
    void close();
}
