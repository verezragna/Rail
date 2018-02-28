package railroad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import railroad.DAO.UserDAO;
import railroad.Exceptions.ApiException;
import railroad.Exceptions.UserAlreadyExistsException;
import railroad.Exceptions.UserNotFoundException;
import railroad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import railroad.model.enums.UserStatus;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@EnableTransactionManagement
@Transactional
public class UserServiceImpl extends BaseService<Long, User> implements UserService {

    @Autowired
    UserDAO userDAO;

    public List<User> list() {
        return userDAO.list();
    }

    public Long save(User user) {
        userDAO.save(user);
        putEntity(user.getId(), user);
        return user.getId();
    }

    public User find(Long id) {
        User user = getCachedEntity(id);
        if (user == null) {
            user = userDAO.find(id);
            putEntity(user.getId(), user);
        }
        return user;
    }

    public void remove(Long id) {
        userDAO.remove(id, UserStatus.DELETED);
        User user = getCachedEntity(id);
        if (user != null) {
            user.setStatus(UserStatus.DELETED);
            putEntity(id, user);
        }
    }
}