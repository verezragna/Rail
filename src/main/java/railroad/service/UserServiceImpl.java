package railroad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import railroad.DAO.UserDAO;
import railroad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import railroad.model.enums.UserStatus;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public List<User> list() {
        return userDAO.list();
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public User find(Long id) {
        return userDAO.find(id);
    }

    public void remove(Long id) {
        userDAO.remove(id, UserStatus.DELETED);
    }
}
