package com.vehicle.vehicalmanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.vehicle.vehicalmanagement.bean.Vehicle;
import com.vehicle.vehicalmanagement.dto.VehicleDto;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

	Vehicle mapVehicleDtoToVehicle(VehicleDto vehicleDto);

	VehicleDto mapVehicleToVehicleDto(Vehicle vehicle);
	List<VehicleDto> mapVehicleListToVehicleDtoList(List<Vehicle> vehicle);
}
