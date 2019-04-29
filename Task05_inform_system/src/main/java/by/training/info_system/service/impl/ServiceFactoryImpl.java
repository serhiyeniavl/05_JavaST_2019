package by.training.info_system.service.impl;

import by.training.info_system.dao.DaoCreator;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.CarService;
import by.training.info_system.service.OrderService;
import by.training.info_system.service.Service;
import by.training.info_system.service.ServiceFactory;
import by.training.info_system.service.UserService;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class ServiceFactoryImpl implements ServiceFactory {
    private static final Map<Class<? extends Service>, Class<? extends AbstractService>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(CarService.class, CarServiceImpl.class);
        SERVICES.put(UserService.class, UserServiceImpl.class);
        SERVICES.put(OrderService.class, OrderServiceImpl.class);
    }

    private DaoCreator creator;


    public ServiceFactoryImpl(final DaoCreator creator) {
        this.creator = creator;
    }

    @Override
    public <T extends Service> Optional<T> getService(final Class<T> key) {
        Class<? extends AbstractService> value = SERVICES.get(key);
        if (value != null) {
            try {
                AbstractService service = value.getDeclaredConstructor(null).newInstance(null);
                service.setDaoCreator(creator);
                return Optional.of((T)service);
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
    public void close() {
        creator.close();
    }
}
