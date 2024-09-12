package com.shohochori.bondhu.assistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
    Assistant findByEmail(String email);
    @Query(value = "SELECT *, ST_Distance_Sphere(location, POINT(?1, ?2)) AS distance " +
            "FROM assistant " +
            "WHERE ST_Distance_Sphere(location, POINT(?1, ?2)) <= ?3", nativeQuery = true)
    List<Assistant> findAssistantsWithinDistance(double latitude, double longitude, double distance);
}