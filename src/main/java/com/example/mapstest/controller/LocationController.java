package com.example.mapstest.controller;

import com.example.mapstest.DTO.LocationDTO;
import com.example.mapstest.DTO.PostDTO;
import com.example.mapstest.DTO.ResponseAPI;
import com.example.mapstest.DTO.VisitorDistanceDTO;
import com.example.mapstest.model.MyUser;
import com.example.mapstest.model.UserLocation;
import com.example.mapstest.service.LocationService;
import com.example.mapstest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    @PostMapping("/add")
    public ResponseEntity<ResponseAPI> addLocation (@RequestBody LocationDTO userLocation){
        locationService.addLocation(userLocation);
        return ResponseEntity.status(200).body(new ResponseAPI("location Added",200));
    }

    @GetMapping("/getdistance/{id}")
    public ResponseEntity<List<PostDTO>> getDistance(@PathVariable Integer id){
        return ResponseEntity.status(200).body(locationService.getDistance(id));
    }
    @PostMapping("/getdistancevisitor")
    public ResponseEntity <List<PostDTO>> getVisitorDistance(@RequestBody VisitorDistanceDTO visitorDistanceDTO){
        return ResponseEntity.status(200).body(locationService.getVisitorDistance(visitorDistanceDTO));
    }
}
