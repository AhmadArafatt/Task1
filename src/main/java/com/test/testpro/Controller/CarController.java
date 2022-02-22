package com.test.testpro.Controller;

import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Car;
import com.test.testpro.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CarController {

    private  CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/getCars")
    public List<Car> car() {
       return carService.findAllByRent(false);
    }


    @GetMapping("/rent/{id}/{rentBy}/{rentalPeriod}")
    public String rent(@PathVariable int id, @PathVariable String rentBy, @PathVariable String rentalPeriod, HttpServletResponse response) {
        try {


            List<Car> l = carService.findAllByRent(false);
            carService.rent(l, id, rentBy, rentalPeriod);
            return "Your rent have been done";
        }
              catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id not found", exc);
        }
        }

    Thread R1= new Thread(() -> {
        System.out.println("Thread 1 get list");
        List<Car> l= carService.findAllByRent(false);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread 1 try to rent");
        carService.rent(l,1,"Ahmad","3 days");
    });

    Thread R2= new Thread(() -> {
        System.out.println("Thread 2 get list");
        List<Car>  l2= carService.findAllByRent(false);
        System.out.println("Thread 2 try to rent");
        carService.rent(l2,1,"Ali","2 Days");
    });


    @GetMapping(value = "/test")
    public  void te() {
        R1.start();
        R2.start();
    }
    @GetMapping("/testexception")
    public List<Car> testException() {
        throw new ApiRequestException("Test Exception Handling");
    }
}//  Not going to render a view out.
// Our view is going to call this controller and perform some action and return sth to view
