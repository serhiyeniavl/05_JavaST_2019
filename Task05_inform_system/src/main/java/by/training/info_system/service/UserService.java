package by.training.info_system.service;

import by.training.info_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service {
    Optional<User> findByLogin(final String email);
    Optional<List<User>> readBlackList();
    Optional<List<User>> findUsersWithDiscount();
}
