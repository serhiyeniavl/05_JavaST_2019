package by.training.info_system.service;

import by.training.info_system.entity.BlackListNode;
import by.training.info_system.entity.User;
import by.training.info_system.entity.role.Role;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service {
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailFullInto(String email);
    Integer registerNewUser(User user);
    Integer countUsers();
    boolean isInBlackList(User user);
    boolean updateRole(long id, Role role);
    boolean delete(long id);
    boolean deleteFromBlackList(long id);
    boolean updatePassword(long id, String password);
    boolean updateEmail(long id, String email);
    boolean isExist(Integer passportNumber, String idPassportNumber);
    boolean moveToBlackList(BlackListNode node);
    Optional<List<User>> findAll(int page, int recordsPerPage);
    Optional<List<User>> findManagers(int page, int recordsPerPage);
    Optional<List<User>> findCustomers(int page, int recordsPerPage);
    Optional<List<BlackListNode>> readBlackList();
    Optional<List<BlackListNode>> readBlackList(int page, int recordsPerPage);
}
