package com.example.Javadeveloperproblem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserLocationController {

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Autowired
    private UserLocationService userLocationService;

    @PostMapping("/create_data")
    public void createTable() {
        // Spring Data JPA will create the 'user_location' table automatically
    }

    @PostMapping("/update_data")
    public void updateUserLocation(@RequestBody UserLocation userLocation) {
        userLocationRepository.save(userLocation);
    }

    @GetMapping("/get_users/{n}")
    public List<UserLocation> getNearestUsers(@PathVariable int n) {
        List<UserLocation> nearestUsers = userLocationService.findNearestUsers(n);
        // Return the nearest users
        return nearestUsers;
    }
}
