package com.test.testpro.service;

import com.test.testpro.model.User;
import com.test.testpro.repository.UserRepository;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@EnableTransactionManagement
@EnableRetry

@Transactional(isolation = Isolation.SERIALIZABLE)
public class UserService {

        private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> getUser(long id){
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}


