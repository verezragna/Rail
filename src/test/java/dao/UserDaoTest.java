package dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import railroad.DAO.UserDAO;
import railroad.model.User;
import railroad.model.enums.UserStatus;
import java.util.*;

import static org.junit.Assert.*;

public class UserDaoTest extends BaseDaoTest {

    @Autowired private UserDAO userDao;
    private final Long correctId = 1L;
    private final Long incorrectId = -1L;

    @Test
    public void findUserExistsReturnFoundUser() {
        User user = createUser(1L);

        assertEquals("Values must be equals", user, userDao.find(correctId));
    }

    @Test
    public void findUserNotExistsReturnNull() {
        assertNull("Value must be null", userDao.find(incorrectId));
    }

    @Test
    public void findListUsersExistReturnUserList() {
        List<User> userList = createUserList(4, 1L, UserStatus.ACTIVE);

        assertEquals("Values must be equals", userList, userDao.list());
    }

    @Test
    public void persistUserNotExistsReturnIdCreatedUser() {
        User user = createUserWithoutId();

        userDao.save(user);

        assertEquals("Values must be equals", user, entityManager.find(User.class, user.getId()));
    }


    @Test
    public void removeUserExistsFindShouldChangeStatus() {
        userDao.remove(correctId, UserStatus.DELETED);

        User user = entityManager.find(User.class, correctId);
        assertEquals("Value must be equals", UserStatus.DELETED, user.getStatus());
    }

    private User createUser(Long id) {
        User user = new User(id.toString(), id.toString(), id.toString(), id.toString(), UserStatus.ACTIVE);
        user.setId(id);
        return user;
    }

    private User createUserWithoutId() {
        return new User("Test", "Test", "Test", "Test", UserStatus.ACTIVE);
    }

    private List<User> createUserList(int size, Long startId, UserStatus userStatus) {
        List<User> userList = new ArrayList<User>(size);
        for (int i = 0; i < size; i++) {
            User user = new User(String.valueOf(startId+i), String.valueOf(startId+i), String.valueOf(startId+i), String.valueOf(startId+i), userStatus);
            user.setId(startId + i);
            userList.add(user);
        }
        return userList;
    }

}