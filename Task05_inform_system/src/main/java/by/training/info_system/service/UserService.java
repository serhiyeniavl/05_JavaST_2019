package by.training.info_system.service;

import by.training.info_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service {
    Optional<User> findByEmail(String email);
    Integer registerNewUser(User user);
    boolean isInBlackList(User user);
    boolean isExist(Integer passportNumber, String idPassportNumber);
    Optional<List<User>> readBlackList();
    Optional<List<User>> findUsersWithDiscount();
}
