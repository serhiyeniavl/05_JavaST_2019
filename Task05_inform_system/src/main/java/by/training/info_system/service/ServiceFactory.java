package by.training.info_system.service;

import java.util.Optional;

public interface ServiceFactory {
    <T extends Service> Optional<T> getService(final Class<T> key);

    void close();
}
