package com.example.mapstest.service;

import com.example.mapstest.Exception.UserNotFoundException;
import com.example.mapstest.model.MyUser;
import com.example.mapstest.model.UserLocation;
import com.example.mapstest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser (MyUser user){
        userRepository.save(user);
    }
    public MyUser getUserById(Integer id){
       MyUser user =  userRepository.findById(id).get();
       return user;
    }
    public MyUser getUserByUsername(String username){
        return userRepository.findMyUserByUsername(username).orElseThrow(()->{
            throw new UserNotFoundException("User doesn't exist");
        });
    }
    public List<MyUser> getAllUsers(){
        return userRepository.findAll();
    }


}
