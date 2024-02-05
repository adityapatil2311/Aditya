package com.vehicle.vehicalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicle.vehicalmanagement.bean.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
