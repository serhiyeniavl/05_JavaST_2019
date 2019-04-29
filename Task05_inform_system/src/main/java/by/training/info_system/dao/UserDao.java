package by.training.info_system.dao;

import by.training.info_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> read(final String email);
    Optional<List<User>> readBlackList();
    Optional<List<User>> findAllWithDiscount();
}
