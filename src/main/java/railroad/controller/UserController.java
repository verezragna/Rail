package railroad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import railroad.Exceptions.ApiException;
import railroad.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import railroad.service.UserService;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@EnableAspectJAutoProxy
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('GUEST', 'ADMIN')")
    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity getUser(@PathVariable Long id) throws JsonProcessingException {
        try {
            Object user = userService.find(id);
            return user == null ? createSuccessResponse() : createSuccessResponse(user);
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse(ex);
        }
    }

    @PreAuthorize("hasAnyAuthority('GUEST', 'ADMIN')")
    @GetMapping
    public @ResponseBody
    ResponseEntity getUserList() throws JsonProcessingException {
        try {
            return createSuccessResponse(userService.list());
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse(ex);
        }
    }

    @PreAuthorize("hasAnyAuthority('GUEST', 'ADMIN')")
    @PutMapping
    public @ResponseBody
    ResponseEntity updateUser(@RequestBody User user) throws JsonProcessingException {
        try {
            userService.save(user);
            return createSuccessResponse();
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse(ex);
        }
    }

    @PreAuthorize("hasAnyAuthority('GUEST', 'ADMIN')")
    @PostMapping
    public @ResponseBody
    ResponseEntity createUser(@RequestBody User user) throws JsonProcessingException {
        try {
            userService.save(user);
            return createSuccessResponse(user.getId());
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse(ex);
        }
    }

    @PreAuthorize("hasAnyAuthority('GUEST', 'ADMIN')")
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity deleteUser(@PathVariable Long id) throws JsonProcessingException {
        try {
            userService.remove(id);
            return createSuccessResponse(id);
        } catch (ApiException ex) {
            return createFailedResponse(ex);
        } catch (Exception ex) {
            return createInternalErrorResponse(ex);
        }
    }

}