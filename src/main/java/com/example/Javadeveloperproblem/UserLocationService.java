package com.example.Javadeveloperproblem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserLocationService {
    @Autowired
    private UserLocationRepository userLocationRepository;

    public List<UserLocation> findNearestUsers(int n) {
        List<UserLocation> allUsers = userLocationRepository.findByExcludedFalse();

        // Sort the users by distance from (0,0) using a custom comparator
        List<UserLocation> nearestUsers = allUsers.stream()
                .sorted(Comparator.comparingDouble(user -> calculateDistance(user.getLatitude(), user.getLongitude(), 0, 0)))
                .limit(n)
                .collect(Collectors.toList());

        return nearestUsers;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double earthRadius = 6371; // Radius of the Earth in kilometers
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
