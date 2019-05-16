package by.training.info_system.dao;

import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.User;
import by.training.info_system.entity.role.Role;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    boolean update(long id, String password);
    boolean update(long id, Role role);
    boolean deleteFromBlackList(long id);
    boolean updateEmail(long id, String email);
    Integer countUsers();
    Optional<User> read(String email);
    Optional<User> readFullInfo(String email);
    Optional<User> findByPassportNumber(Integer number, String idNumber);
    Optional<List<User>> findAllWithDiscount();
    Optional<List<User>> getAll(int pageNum, int recordsPerPage);
    Optional<List<User>> findManagers();
    Optional<List<User>> findCustomers();
    Optional<List<BlackListNode>> readBlackList();
}
