package com.test.testpro.bootstrap;

import com.test.testpro.model.User;
import com.test.testpro.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final UserService userService;

    public DatabaseLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        User Ahmad = new User("Ahmad","arafat.2000@live.com","Nablus","Good");
        userService.save(Ahmad);

    }


}
