package by.training.info_system.service.impl;

import by.training.info_system.dao.UserDao;
import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.User;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends AbstractService implements UserService {
    @Override
    public Optional<User> findByLogin(String email) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.read(email);
    }

    @Override
    public boolean registerNewUser(final User user) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        daoManager.autoCommit(false);
        daoManager.commit();
        boolean isCreated = userDao.create(user);
        if (!isCreated) {
            daoManager.rollback();
        } else {
            daoManager.commit();
        }
        daoManager.autoCommit(true);
        return isCreated;
    }

    @Override
    public boolean isInBlackList(final User user) {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        List<BlackListNode> usersBlackList = dao.readBlackList().orElse(new ArrayList<>());
        return usersBlackList.stream()
                .anyMatch(user1 -> user1.getUser().getLogin().equals(user.getLogin()));
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
