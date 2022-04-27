package com.test.testpro.Controller;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Car;
import com.test.testpro.service.CarService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to this site";
    }


    @GetMapping("/cars")
    public List<Car> car() {
        return carService.findAll();
    }


    @PutMapping("/rent/{id}/{rentBy}/{rentalPeriod}")
    public Car rent(@PathVariable int id, @PathVariable String rentBy, @PathVariable String rentalPeriod, HttpServletResponse response) {
        List<Car> l = carService.findAllByRent(false);
        return carService.rent(l, id, rentBy, rentalPeriod);
    }

    @PostMapping("car")
    public Car add(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping("/testexception")
    public List<Car> testException() {
        throw new ApiRequestException("Test Exception Handling");
    }
}//  Not going to render a view out.
// Our view is going to call this controller and perform some action and return sth to view
