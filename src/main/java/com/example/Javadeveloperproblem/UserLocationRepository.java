package com.example.Javadeveloperproblem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLocationRepository extends JpaRepository<UserLocation,Long> {
    List<UserLocation> findByExcludedFalse();
}
