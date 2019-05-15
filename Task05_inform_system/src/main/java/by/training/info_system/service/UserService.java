package by.training.info_system.service;

import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service {
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailFullInto(String email);
    Integer registerNewUser(User user);
    boolean isInBlackList(User user);
    boolean updatePassword(long id, String password);
    boolean updateEmail(long id, String email);
    boolean isExist(Integer passportNumber, String idPassportNumber);
    Optional<List<User>> findAll();
    Optional<List<User>> findManagers();
    Optional<List<User>> findCustomers();
    Optional<List<BlackListNode>> readBlackList();
    Optional<List<User>> findUsersWithDiscount();
}
