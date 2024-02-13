package com.vehicle.vehicalmanagement.service;

import java.util.List;

import com.vehicle.vehicalmanagement.bean.Vehicle;
import com.vehicle.vehicalmanagement.dto.VehicleDto;
import com.vehicle.vehicalmanagement.payload.ApiResponse;

public interface VehicleService {

	ApiResponse<VehicleDto> getAllVehicles(int pageNumber, int pageSize);

	ApiResponse<VehicleDto> addVehicle(VehicleDto vehicledto);

	ApiResponse<VehicleDto> UpdateVehicle(VehicleDto vehicledto, int id);

	ApiResponse<VehicleDto> deleteVehicleDto(int id);

	ApiResponse<VehicleDto> getById(int id);

	String getVehicleByRestTemplate(int id);

	void exportToPdf();
 
	
	 
	List<Vehicle> FindByOwnerName(String ownerName);
	List<Vehicle> SearchAll(String vehicleRegistrationNumber,String ownerName, String brand );
	
}
