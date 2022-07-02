package com.example.mapstest.controller;

import com.example.mapstest.DTO.ResponseAPI;
import com.example.mapstest.model.MyUser;
import com.example.mapstest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<ResponseAPI> addUser (@RequestBody MyUser user){
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ResponseAPI("user Added",200));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(){
        return ResponseEntity.status(200).body(new ResponseAPI("Welcome back",200));
    }

    @GetMapping("/userinfo/{username}")
    public ResponseEntity<MyUser> getUserInfo(@PathVariable String username){
        return ResponseEntity.status(200).body(userService.getUserByUsername(username));
    }
    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
}
