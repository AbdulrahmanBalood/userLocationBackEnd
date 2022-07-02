package com.example.mapstest.service;

import com.example.mapstest.DTO.LocationDTO;
import com.example.mapstest.DTO.PostDTO;
import com.example.mapstest.model.MyUser;
import com.example.mapstest.model.UserLocation;
import com.example.mapstest.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final UserService userService;

    public void addLocation (LocationDTO locationDTO){
        MyUser user = userService.getUserById(locationDTO.getUserID());
        UserLocation userLocation = new UserLocation(null,locationDTO.getLat(),locationDTO.getLng(),user);
        locationRepository.save(userLocation);
    }
    public List<UserLocation> getLocations (){
        return locationRepository.findAll();
    }
    public List<PostDTO> getDistance(Integer id){
        List<MyUser> getAllUsers = userService.getAllUsers();
        MyUser user = userService.getUserById(id);
        Double userLat = user.getUserLocation().getLat();
        Double userLng = user.getUserLocation().getLng();
        List<PostDTO> post = new ArrayList<>();
        for(int i = 0; i < getAllUsers.size(); i++){
            MyUser tempUser = getAllUsers.get(i);
            if(user.getId().equals(tempUser.getId())){
                continue;
            }
            Double tempUserLat = tempUser.getUserLocation().getLat();
            Double temUserLng = tempUser.getUserLocation().getLng();
            Double distance = Math.acos(Math.sin(Math.PI*userLat/180.0)*Math.sin(Math.PI*tempUserLat/180.0)+Math.cos(Math.PI*userLat/180.0)*Math.cos(Math.PI*tempUserLat/180.0)*Math.cos(Math.PI*temUserLng/180.0-Math.PI*userLng/180.0))*6371;
            PostDTO postDTO = new PostDTO(tempUser.getUsername(),distance);
            post.add(postDTO);
        }
        return post;
    }
}
