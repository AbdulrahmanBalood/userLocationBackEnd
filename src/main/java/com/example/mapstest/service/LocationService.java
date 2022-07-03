package com.example.mapstest.service;

import com.example.mapstest.DTO.LocationDTO;
import com.example.mapstest.DTO.PostDTO;
import com.example.mapstest.DTO.VisitorDistanceDTO;
import com.example.mapstest.model.MyUser;
import com.example.mapstest.model.UserLocation;
import com.example.mapstest.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final UserService userService;

    public void addLocation (LocationDTO locationDTO){
        MyUser user = userService.getUserById(locationDTO.getUserID());
        if(!hasLocation(locationDTO.getUserID())){
            UserLocation userLocation = new UserLocation(null,locationDTO.getLat(),locationDTO.getLng(),user);
            locationRepository.save(userLocation);
        }else {
           UserLocation userLocation= locationRepository.findById(locationDTO.getUserID()).get();
           userLocation.setLat(locationDTO.getLat());
           userLocation.setLng(locationDTO.getLng());
           locationRepository.save(userLocation);
        }


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
            Integer intDistance = (Integer)distance.intValue();
            PostDTO postDTO = new PostDTO(tempUser.getUsername(),intDistance);
            post.add(postDTO);
        }
        Collections.sort(post, new Comparator<PostDTO>() {

            public int compare(PostDTO o1, PostDTO o2) {
                return Integer.valueOf(o1.getDistance().compareTo(o2.getDistance()));
            }
        });
        return post;
    }
    public boolean hasLocation(Integer id){
        if(locationRepository.findById(id).isPresent()){
            return true;
        }
        else return false;
    }
    public List<PostDTO> getVisitorDistance(VisitorDistanceDTO visitorDistanceDTO){
        List<MyUser> getAllUsers = userService.getAllUsers();
        Double userLat =visitorDistanceDTO.getLat();
        Double userLng = visitorDistanceDTO.getLng();
        List<PostDTO> post = new ArrayList<>();
        for(int i = 0; i < getAllUsers.size(); i++){
            MyUser tempUser = getAllUsers.get(i);
            Double tempUserLat = tempUser.getUserLocation().getLat();
            Double temUserLng = tempUser.getUserLocation().getLng();
            Double distance = Math.acos(Math.sin(Math.PI*userLat/180.0)*Math.sin(Math.PI*tempUserLat/180.0)+Math.cos(Math.PI*userLat/180.0)*Math.cos(Math.PI*tempUserLat/180.0)*Math.cos(Math.PI*temUserLng/180.0-Math.PI*userLng/180.0))*6371;
            Integer intDistance = (Integer)distance.intValue();
            PostDTO postDTO = new PostDTO(tempUser.getUsername(),intDistance);
            post.add(postDTO);
        }
        Collections.sort(post, new Comparator<PostDTO>() {

            public int compare(PostDTO o1, PostDTO o2) {
                return Integer.valueOf(o1.getDistance().compareTo(o2.getDistance()));
            }
        });
        return post;
    }
}
