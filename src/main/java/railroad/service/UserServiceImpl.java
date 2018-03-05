package railroad.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import railroad.DAO.UserDAO;
import railroad.model.Role;
import railroad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import railroad.model.enums.UserRoles;
import railroad.model.enums.UserStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@EnableTransactionManagement
@Transactional
public class UserServiceImpl extends BaseService<Long, User> implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> list() {
        return userDAO.list();
    }

    public Long save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(UserRoles.USER));
        user.setUserRole(roles);
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

    @Override
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
}