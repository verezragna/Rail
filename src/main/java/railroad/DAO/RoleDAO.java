package railroad.DAO;

import railroad.model.Role;
import railroad.model.enums.UserStatus;

import java.util.List;

public interface RoleDAO {

    void save(Role role);

    Role find(Long id);

    void remove(Long id, UserStatus userStatus);

    List<Role> list();
}