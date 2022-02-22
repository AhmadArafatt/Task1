package com.test.testpro.service;

import com.test.testpro.model.Car;
import com.test.testpro.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Random;

@Service
@EnableTransactionManagement
@EnableRetry

@Transactional(isolation = Isolation.SERIALIZABLE)
public class CarService {

        private CarRepository carRepository;

        public Random random=new Random();
        private static final Logger logger = LoggerFactory.getLogger(CarService.class);

        public CarService(CarRepository carRepository) {
            this.carRepository = carRepository;
        }
        public void save(Car car){
            carRepository.save(car);
        }
        public List<Car> findAllByRent(boolean b){
            return carRepository.findAllByRent(b);
        }


        public void rent ( List<Car> l,int id, String rentBy, String rentalPeriod) {
              Car car = l.get(id-1);
              logger.info("Car rent is : "+car.isRent());
              car.setRent(true);
              logger.info("Car rent now is : "+car.isRent()+" By "+rentBy);
              car.setRentBy(rentBy);
              car.setRentalPeriod(rentalPeriod);
              save(car);
              logger.info("Car rented by : "+car.getRentBy());
        }

        @Retryable(maxAttempts = 9)
        public void rentRandom (List<Car> l, String rentBy, String rentalPeriod){
            int rentId2=random.nextInt(2);
            logger.info("Trying to rent a car for "+rentBy +" "+rentId2);
                Car car = l.get(rentId2);
                car.setRent(true);
                car.setRentBy(rentBy);
                car.setRentalPeriod(rentalPeriod);
                save(car);
        }
        @Recover
        public void recover(Exception exception) {
            System.out.println("HelloService.recover");
        }
}


