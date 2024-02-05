package com.vehicle.vehicalmanagement.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.vehicle.vehicalmanagement.bean.Vehicle;
import com.vehicle.vehicalmanagement.dto.VehicleDto;
import com.vehicle.vehicalmanagement.payload.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface VehicleService {

	ApiResponse<VehicleDto> getAllVehicles(int pageNumber, int pageSize);

	ApiResponse<VehicleDto> addVehicle(VehicleDto vehicledto);

	ApiResponse<VehicleDto> UpdateVehicle(VehicleDto vehicledto, int id);

	ApiResponse<VehicleDto> deleteVehicleDto(int id);

	ApiResponse<VehicleDto> getById(int id);

	String getVehicleByRestTemplate(int id);

	void exportToPdf();

}
