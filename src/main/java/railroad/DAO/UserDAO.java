package railroad.DAO;

import railroad.model.User;
import railroad.model.enums.UserStatus;

import java.util.List;

public interface UserDAO {

    void save(User user);

    User find(Long id);

    void remove(Long id, UserStatus userStatus);

    List<User> list();
}