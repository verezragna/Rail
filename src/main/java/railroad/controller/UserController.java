package railroad.controller;

import railroad.Responses.Response;
import railroad.Responses.SuccessResponse;
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
    Response getUser(@PathVariable Long id) {

        return new SuccessResponse(userService.find(id));
    }

    @GetMapping
    public @ResponseBody
    Response getUserList() {
        return new SuccessResponse(userService.list());
    }

    @PutMapping
    public @ResponseBody Response updateUser(@RequestBody User user) {
        userService.save(user);
        return new SuccessResponse(user.getId());
    }

    @PostMapping
    public @ResponseBody Response createUser(@RequestBody User user) {
        userService.save(user);
        return new SuccessResponse(user.getId());
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Response deleteUser(@PathVariable Long id) {
        this.userService.remove(id);
        return new SuccessResponse(id);
    }
}