package com.shohochori.bondhu.pendingrequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PendingRequestRepository extends JpaRepository<PendingRequest, Integer> {
    @Query(value = "SELECT * FROM pending_request " +
            "WHERE ST_Distance_Sphere(POINT(:assistantLongitude, :assistantLatitude), POINT(longitude, latitude)) <= :distance"+
            " AND status = 'pending'",
            nativeQuery = true)
    List<PendingRequest> findPendingRequestsWithinDistance(double assistantLatitude, double assistantLongitude, double distance);

    List<PendingRequest> findByUserId(Integer userId);
}
