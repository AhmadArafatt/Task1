package com.test.testpro.Controller;

import com.test.testpro.Config.UserConfig;
import com.test.testpro.exception.ApiRequestException;
import com.test.testpro.model.Car;
import com.test.testpro.model.User;
import com.test.testpro.service.CarService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private UserConfig userConfig;
    private  String url;

    public CarController(CarService carService,UserConfig userConfig) {
        this.userConfig=userConfig;
        this.carService = carService;



    }

    @GetMapping("/welcome")
    public String home() {
        return "Welcome to this site";
    }


    @GetMapping("/available")
    public List<Car> car() {
        return carService.findAll();
    }

    @GetMapping("/user/{id}")
    public  User user(@PathVariable long id) {
        url=String.format("http://%s:%s/users/%d",this.userConfig.getHost(),this.userConfig.getPort(),id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> usersInfo = restTemplate.getForEntity(url, User.class);
        User user=usersInfo.getBody();
        if(user!=null)
        return user;
        else throw new ApiRequestException("User doesnt found");
    }

    @GetMapping("/ho/{id}")
    public String ho(@PathVariable long id) {
         url=String.format("http://%s:%s/users/%d",this.userConfig.getHost(),this.userConfig.getPort(),id);
        return url;
    }



    @PutMapping("/rent/{id}/{rentBy}/{rentalPeriod}")
    public Car rent(@PathVariable int id, @PathVariable String rentBy, @PathVariable String rentalPeriod, HttpServletResponse response) {
        List<Car> l = carService.findAllByRent(false);
        return carService.rent(l, id, rentBy, rentalPeriod);
    }

    @PostMapping("/car")
    public Car add(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping("/testexception")
    public List<Car> testException() {
        throw new ApiRequestException("Test Exception Handling");
    }
}//  Not going to render a view out.
// Our view is going to call this controller and perform some action and return sth to view
