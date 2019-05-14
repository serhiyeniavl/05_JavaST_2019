package by.training.info_system.dao;

import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    boolean update(long id, String password);
    Optional<User> read(String email);
    Optional<List<BlackListNode>> readBlackList();
    Optional<User> findByPassportNumber(Integer number, String idNumber);
    Optional<List<User>> findAllWithDiscount();
}
