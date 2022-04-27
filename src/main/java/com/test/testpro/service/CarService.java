package com.test.testpro.service;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Car;
import com.test.testpro.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@EnableTransactionManagement
@EnableRetry

@Transactional(isolation = Isolation.SERIALIZABLE)
public class CarService {

    private final CarRepository carRepository;
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public Car save(Car car){
        carRepository.save(car);
        return car;
    }
    public List<Car> findAllByRent(boolean b){
        return carRepository.findAllByRent(b);
    }
    public Car rent ( List<Car> l,int id, String rentBy, String rentalPeriod) {
        try {
            Car car = l.get(id - 1);
            car.setRent(true);
            car.setRentBy(rentBy);
            car.setRentalPeriod(rentalPeriod);
            save(car);
            return car;
        }
        catch (Exception e){
            throw new ApiRequestException("Your rent have not been done successfully");
        }
    }
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}


