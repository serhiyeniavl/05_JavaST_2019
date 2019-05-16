package by.training.info_system.service.impl;

import by.training.info_system.dao.UserDao;
import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.User;
import by.training.info_system.entity.data.UserData;
import by.training.info_system.entity.role.Role;
import by.training.info_system.service.AbstractService;
import by.training.info_system.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends AbstractService implements UserService {
    @Override
    public Optional<User> findByEmail(final String email) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.read(email);
    }

    @Override
    public Optional<User> findByEmailFullInto(final String email) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.readFullInfo(email);
    }

    @Override
    public Optional<User> findById(final long id) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.get(id);
    }

    @Override
    public boolean updatePassword(final long id, final String password) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.update(id, password);
    }

    @Override
    public boolean updateEmail(final long id, final String email) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.updateEmail(id, email);
    }

    @Override
    public boolean updateRole(final long id, final Role role) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        return userDao.update(id, role);
    }

    @Override
    public Integer registerNewUser(final User user) {
        UserDao userDao = daoManager.createDao(UserDao.class).orElseThrow();
        daoManager.autoCommit(false);
        daoManager.commit();
        int isCreated = userDao.create(user);
        if (isCreated != 0) {
            daoManager.commit();
        } else {
            daoManager.rollback();
        }
        daoManager.autoCommit(true);
        return isCreated;
    }

    @Override
    public boolean isInBlackList(final User user) {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        List<BlackListNode> usersBlackList = dao.readBlackList().orElse(new ArrayList<>());
        return usersBlackList.stream()
                .anyMatch(user1 -> user1.getUser().getEmail().equals(user.getEmail()));
    }

    @Override
    public boolean isExist(final Integer passportNumber,
                           final String idPassportNumber) {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        return dao.findByPassportNumber(passportNumber, idPassportNumber).isPresent();
    }

    @Override
    public Optional<List<User>> findAll() {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        return dao.getAll();
    }

    @Override
    public Optional<List<User>> findManagers() {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        return dao.findManagers();
    }

    @Override
    public Optional<List<User>> findCustomers() {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        return dao.findCustomers();
    }

    @Override
    public Optional<List<BlackListNode>> readBlackList() {
        UserDao dao = daoManager.createDao(UserDao.class).orElseThrow();
        return dao.readBlackList();
    }

    @Override
    public Optional<List<User>> findUsersWithDiscount() {
        return Optional.empty();
    }
}
