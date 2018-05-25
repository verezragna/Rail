package railroad.DAO;

import railroad.Exceptions.UserNotFoundException;
import railroad.model.Role;
import org.springframework.stereotype.Repository;
import railroad.model.enums.UserStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {

    @Override
    public List<Role> list() {
        return super.list();
    }

    @Override
    public void save(Role role) {
        super.save(role);
    }

    @Override
    public Role find(Long id) {
        Role role = super.find(id);
        if (role == null) {
            throw new UserNotFoundException();
        } else return role;
    }

    @Override
    public void removeModel(Role role) {
        super.removeModel(role);
    }

    @Override
    public void remove(Long id, UserStatus userStatus) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", userStatus);
        params.put("id", id);
        executeNamedQuery("changeUserStatus", params);
    }
}
