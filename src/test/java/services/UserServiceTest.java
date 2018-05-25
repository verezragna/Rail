package services;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AssertionErrors;
import railroad.DAO.UserDAO;
import railroad.Exceptions.UserNotFoundException;
import railroad.model.User;
import railroad.model.enums.UserStatus;
import railroad.service.UserService;
import railroad.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest extends BaseServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDAO userDAO;
    private final Long correctId = 1L;
    private final Long incorrectId = -1L;

    @Test
    public void findUserExistsReturnFoundUser() {
        User user = createUser(1L);

        when(userDAO.find(correctId)).thenReturn(user);

        assertEquals("Values must be equals", user, userService.find(correctId));
    }

    @Test
    public void findUserNotExistsReturnEmptyModel() {
        when(userDAO.find(incorrectId)).thenThrow(new UserNotFoundException());

        try {
            userService.find(incorrectId);
            fail();
        } catch (UserNotFoundException e) {
            assertNotEquals("", e.getMessage());
        }
        verify(userDAO, times(1)).find(any(Long.class));
    }

    @Test
    public void findListUsersExistReturnUserList() {
        List<User> userList = createUserList(2);

        when(userDAO.list()).thenReturn(userList);

        assertEquals("Values must be equals", userService.list(), userList);
    }

    @Test
    public void findListUsersNotExistReturnEmptyUserList() {
        List<User> userList = createUserList(0);

        when(userDAO.list()).thenReturn(userList);

        assertEquals("Values must be equals", userService.list(), userList);
    }

    @Test
    public void persistUserNotExistsReturnIdCreatedUser() {
        User user = createUserWithoutId();
        userService.save(user);
        verify(userDAO, times(1)).save(any(User.class));
    }

    @Test
    public void removeUserExistsReturnNothing() {
        doNothing().when(userDAO).remove(correctId, UserStatus.DELETED);

        userService.remove(correctId);
    }

    @Test(expected = UserNotFoundException.class)
    public void removeUserNotExistsThrowUserNotFoundException() {
        doThrow(new UserNotFoundException()).when(userDAO).remove(incorrectId, UserStatus.DELETED);

        userService.remove(incorrectId);
    }

    private User createUser(Long id) {
        User user = new User(id.toString(), id.toString(), id.toString(), id.toString(), UserStatus.ACTIVE);
        user.setId(id);
        return user;
    }

    private User createUserWithoutId() {
        return new User("Test", "Test", "Test", "Test", UserStatus.ACTIVE);
    }

    private List<User> createUserList(int size) {
        List<User> UserList = new ArrayList<User>(size);
        for (int i = 0; i < size; i++) {
            Long id = new Random(0).nextLong();
            UserList.add(new User(id.toString(), id.toString(), id.toString(), id.toString(), UserStatus.ACTIVE));
        }
        return UserList;
    }

}