package com.exer.springexer.controller;

import com.exer.springexer.dto.Result;
import com.exer.springexer.entity.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exer.springexer.service.UserService;

@Tag(name = "user")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        System.out.println("trigger");
        this.userService=userService;
    }


    @GetMapping("/test")
    public Result getUser(){
        return new Result(321);
    }

    @PostMapping("/test")
    public Result getUser1(){
        return new Result(123);
    }

    @PostMapping("/createUser")
    public Result createUser(@RequestBody Users users){
        return userService.creatUser(users);
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody Users users){
        return userService.updateUser(users);
    }

    @GetMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }


    @PostMapping("/login")
    public Result login(@RequestBody Users user){
        return   userService.login(user);
    }
    @PostMapping("/ChangePass")
    public Result ChangePass(@RequestBody Users user){
        return userService.ChangePass(user);
    }






}
