package com.test.testpro.Controller;

import com.test.testpro.model.User;
import com.test.testpro.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/info/{id}")
    public Optional<User> getUserById(@PathVariable long id){
        return userService.getUser(id);
    }
}//  Not going to render a view out.
// Our view is going to call this controller and perform some action and return sth to view
