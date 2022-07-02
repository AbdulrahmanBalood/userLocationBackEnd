package com.example.mapstest.repository;

import com.example.mapstest.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Integer> {
    Optional<MyUser> findMyUserByUsername(String username);
}
