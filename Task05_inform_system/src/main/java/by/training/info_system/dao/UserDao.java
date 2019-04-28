package by.training.info_system.dao;

import by.training.info_system.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserToSignIn(final String email);
}
