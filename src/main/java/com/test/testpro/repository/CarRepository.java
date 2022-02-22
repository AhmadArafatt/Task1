package com.test.testpro.repository;


import com.test.testpro.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
//@Transactional(isolation = Isolation.SERIALIZABLE)
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByRent(boolean b);
   // Optional<Cars> findByEmailAndActivationCode(String email, String activationCode);
}
