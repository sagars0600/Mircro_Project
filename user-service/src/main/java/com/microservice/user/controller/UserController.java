package com.microservice.user.controller;

import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;
import com.microservice.user.valueObjects.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId){
        return userService.getUserWithDepartment(userId);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> list = userService.getCustomer();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        Optional<User> one = userService.findById(id);
        return new ResponseEntity<User>(one.get(),HttpStatus.OK);
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<User> updateUserByID(@RequestBody User user ,@PathVariable("id") Long id){
//        return userService.updateUser(user,id);
//    }
        @PutMapping("/update")
        public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
        }
}
