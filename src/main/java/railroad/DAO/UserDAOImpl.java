package railroad.DAO;

import org.springframework.transaction.annotation.Transactional;
import railroad.Exceptions.ApiException;
import railroad.Exceptions.UserNotFoundException;
import railroad.model.User;
import org.springframework.stereotype.Repository;
import railroad.model.enums.UserStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    @Override
    public List<User> list() {
        return super.list();
    }

    @Override
    public void save(User entity) {
        super.save(entity);
    }

    @Override
    public User find(Long id) {
        User user = super.find(id);
        if (user == null) {
            throw new UserNotFoundException();
        } else return user;
    }

    @Override
    public void removeModel(User entity) {
        super.removeModel(entity);
    }

    @Override
    public void remove(Long id, UserStatus userStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", userStatus);
        params.put("id", id);
        executeNamedQuery("changeUserStatus", params);
    }
}
