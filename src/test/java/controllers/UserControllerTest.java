package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import railroad.Exceptions.ApiException;
import railroad.Exceptions.UserAlreadyExistsException;
import railroad.Exceptions.UserNotFoundException;
import railroad.Responses.FailedResponse;
import railroad.Responses.SuccessResponse;
import railroad.controller.UserController;
import railroad.model.User;
import railroad.model.enums.UserStatus;
import railroad.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;

public class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    @Autowired
    private UserController userController;
    @Mock
    private UserService userService;

    private final Long correctId = 1L;
    private final Long incorrectId = -1L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findUserExistsReturnSuccessResponseWithFoundUser() throws Exception {
        User user = createUser(1L);

        when(userService.find(correctId)).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", correctId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.result.id", is(1)));
        verify(userService, times(1)).find(any(Long.class));
    }

    @Test
    public void findUserNonExistsReturnFailedResponseWithFalseStatus() throws Exception {
        when(userService.find(incorrectId)).thenThrow(new UserNotFoundException());

        mockMvc.perform(get("/api/users/{id}", incorrectId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.code", is("USER_NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("User doesn't exist!")));
        verify(userService, times(1)).find(any(Long.class));
    }

    @Test
    public void findListUsersExistReturnSuccessResponseWithUserList() throws Exception {
        List<User> userList = createUserList(2);

        when(userService.list()).thenReturn(userList);

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result[0].id", is(1)))
                .andExpect(jsonPath("$.result[1].id", is(2)));

        verify(userService, times(1)).list();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void createUserTest() throws Exception {
        User user = createUser(1L);
        String userJsonStr = new ObjectMapper().writeValueAsString(user);
        mockMvc.perform(
                post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJsonStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.result", is(1)));

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void createUserWithExistLoginTest() throws Exception {
        User user = createUser(1L);
        when(userService.save(user)).thenReturn(1L);
        String userJsonStr = new ObjectMapper().writeValueAsString(user);
        when(userService.save(user)).thenThrow(new UserAlreadyExistsException());
        mockMvc.perform(
                post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJsonStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.code", is("USER_ALREADY_EXISTS")));
    }

    @Test
    public void removeUserExistsReturnSuccessResponse() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.result", is(1)));

        verify(userService, times(1)).remove(any(Long.class));
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUserWithExistLoginTest() throws Exception {
        User user = createUser(1L);
        when(userService.save(user)).thenReturn(1L);
        String userJsonStr = new ObjectMapper().writeValueAsString(user);
        when(userService.save(user)).thenThrow(new UserAlreadyExistsException());
        mockMvc.perform(
                put("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJsonStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(false)))
                .andExpect(jsonPath("$.code", is("USER_ALREADY_EXISTS")));
    }

    @Test
    public void updateUserTest() throws Exception {
        User user = createUser(1L);
        when(userService.save(user)).thenReturn(1L);
        user.setFirst_name("Test2");
        String userJsonStr = new ObjectMapper().writeValueAsString(user);
        mockMvc.perform(
                put("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJsonStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)));

        verify(userService, times(1)).save(any(User.class));
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
        List<User> userList = new ArrayList<User>(size);
        for (long i = 1; i <= size; i++) {
            User user = new User(String.valueOf(i), String.valueOf(i), String.valueOf(i), String.valueOf(i), UserStatus.ACTIVE);
            user.setId(i);
            userList.add(user);
        }
        return userList;
    }

}