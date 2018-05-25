package railroad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import railroad.Exceptions.ApiException;
import railroad.Exceptions.UserNotFoundException;
import railroad.model.User;
import railroad.service.UserService;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@Controller
@RequestMapping("")
public class LoginController extends BaseController {
    @Autowired private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginError")
    public @ResponseBody
    ResponseEntity loginError() throws JsonProcessingException {
        return createFailedResponse(new UserNotFoundException());

    }

}
