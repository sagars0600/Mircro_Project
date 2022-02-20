package com.microservice.user.service;

import com.microservice.user.Exception.UserNotFoundException;
import com.microservice.user.entity.User;
import com.microservice.user.repository.UserRepo;
import com.microservice.user.valueObjects.Department;
import com.microservice.user.valueObjects.ResponseTemplateVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepo.findByUserId(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        return  vo;
    }

    public List<User> getCustomer() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("Please Check ID");
        return user;
    }

//    public ResponseEntity<User> updateUser(User user, Long id) {
//        return null;
//    }

    public User updateUser(@NotNull User user){
        User exitUser = userRepo.findById(user.getUserId()).orElse(null);
        exitUser.setFirstName(user.getFirstName());
        exitUser.setLastName(user.getLastName());
        exitUser.setEmail(user.getEmail());
        exitUser.setDepartmentId(user.getDepartmentId());
        return userRepo.save(exitUser);
    }
}
