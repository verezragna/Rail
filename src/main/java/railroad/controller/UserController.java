package railroad.controller;

import railroad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import railroad.service.UserService;


import java.util.List;

@Controller
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public @ResponseBody
    User getUser(@PathVariable Long id) {
        return userService.find(id);
    }

    @GetMapping
    public @ResponseBody
    List<User> getUserList() {
        return userService.list();
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping
    public @ResponseBody
    void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.remove(id);
    }
}