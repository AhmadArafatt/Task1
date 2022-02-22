package com.test.testpro.bootstrap;

import com.test.testpro.model.Car;

import com.test.testpro.repository.CarRepository;
import com.test.testpro.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private final CarService carService;

    public DatabaseLoader(CarService carService) {
        this.carService = carService;
    }
    @Override
    public void run(String... args) {
        Car kia = new Car("Kia",2009,"green",false,"None","0");
        carService.save(kia);

        Car bmw = new Car("BMW",2012,"RED",false,"None","0");
        carService.save(bmw);

//        Car skoda = new Car("SKODA",2002,"BLACK",false,"None","0");
//        carService.save(skoda);
    }


}
