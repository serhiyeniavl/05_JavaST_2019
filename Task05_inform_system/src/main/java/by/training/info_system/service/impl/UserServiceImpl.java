package by.training.info_system.service.impl;

import by.training.info_system.dao.UserDao;
import by.training.info_system.entity.User;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends AbstractService implements UserService {
    @Override
    public Optional<User> findByLogin(String email) {
        UserDao userDao = daoCreator.createDao(UserDao.class).orElseThrow();
        return userDao.read(email);
    }

    @Override
    public Optional<List<User>> readBlackList() {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findUsersWithDiscount() {
        return Optional.empty();
    }
}
