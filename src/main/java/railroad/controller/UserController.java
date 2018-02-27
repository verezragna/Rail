package railroad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import railroad.Exceptions.ApiException;
import railroad.Responses.FailedResponse;
import railroad.Responses.SuccessResponse;
import railroad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import railroad.service.UserService;


@Controller
@RequestMapping(value = "/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity getUser(@PathVariable Long id) throws JsonProcessingException {
        try {
            Object user = userService.find(id);
            return user == null ? createSuccessResponse() : createSuccessResponse(user);
        } catch (ApiException e) {
            return createFailedResponse(e);
        } catch (Exception e) {
            return createInternalErrorResponse();
        }
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getUserList() throws JsonProcessingException {
        try {
            return createSuccessResponse(userService.list());
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse();
        }
    }

    @PutMapping
    public @ResponseBody
    ResponseEntity updateUser(@RequestBody User user) throws JsonProcessingException {
        try {
            userService.save(user);
            return createSuccessResponse();
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse();
        }
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity createUser(@RequestBody User user) throws JsonProcessingException {
        try {
            userService.save(user);
            return createSuccessResponse(user.getId());
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse();
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity deleteUser(@PathVariable Long id) throws JsonProcessingException {
        try {
            userService.remove(id);
            return createSuccessResponse(id);
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse();
        }
    }
}