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

    @InjectMocks @Autowired private UserController userController;
    @Mock private UserService userService;

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
                .andExpect(jsonPath("$.status.id", is(1)));
        verify(userService, times(1)).find(any(Long.class));
    }

    /*@Test
    public void findUserNotExistsReturnSuccessResponseWithEmptyUser() throws Exception {
        when(userService.find(incorrectId)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", incorrectId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(new BaseResponse(true))));
    }*/

    @Test
    public void findListUsersExistReturnSuccessResponseWithUserList() throws Exception {
        List<User> userList = createUserList(2);

        when(userService.list()).thenReturn(userList);

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.status", hasSize(2)))
                .andExpect(jsonPath("$.status[0].id", is(1)))
                .andExpect(jsonPath("$.status[1].id", is(2)));

        verify(userService, times(1)).list();
        verifyNoMoreInteractions(userService);
    }

    /*@Test
    public void updateUserExistsReturnSuccessResponseWithUpdatedUser() throws Exception {
        User User = createUser(1L);
        UserDto userDto = createUserDto(1L);
        userDto.setLogin("newLogin");

        when(userService.update(map(userDto, User.class))).thenReturn(User);

        mockMvc.perform(put("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().bytes(objectMapper.writeValueAsBytes(new SuccessResponse(map(User, User.class)))));
    }*/

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
                .andExpect(jsonPath("$.status", is(1)));

        verify(userService, times(1)).save(any(User.class));
    }


    @Test
    public void removeUserExistsReturnSuccessResponse() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.status", is(1)));

        verify(userService, times(1)).remove(any(Long.class));
        verifyNoMoreInteractions(userService);
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