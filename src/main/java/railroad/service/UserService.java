package railroad.service;

import railroad.model.User;
import railroad.model.enums.UserStatus;

import java.util.List;

public interface UserService {

    List<User> list();

    Long save(User user);

    User find(Long id);

    void remove(Long id);

    User findByLogin(String login);
}