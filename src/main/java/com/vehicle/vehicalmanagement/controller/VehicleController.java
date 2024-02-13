package com.vehicle.vehicalmanagement.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.vehicle.vehicalmanagement.bean.Vehicle;
import com.vehicle.vehicalmanagement.dto.VehicleDto;
import com.vehicle.vehicalmanagement.payload.ApiResponse;
import com.vehicle.vehicalmanagement.service.VehicleService;

@RestController
@RequestMapping("/api")
public class VehicleController {

	@Autowired
	private VehicleService service;

	@GetMapping("/getAll")

	public ApiResponse<VehicleDto> getAll(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3", required = false) int pageSize) {
		return service.getAllVehicles(pageNumber, pageSize);
	}

	@PostMapping("/post")

	public ApiResponse<VehicleDto> VehicleSave(@RequestBody VehicleDto vehicledto) {
		return service.addVehicle(vehicledto);

	}

	@DeleteMapping("/delete/{id}")

	public ApiResponse<VehicleDto> DeleteVehicleDto(@PathVariable("id") int id) {
		return service.deleteVehicleDto(id);
	}

	@PutMapping("/update/{id}")

	public ApiResponse<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicledto, @PathVariable("id") int id) {
		return service.UpdateVehicle(vehicledto, id);

	}

	@GetMapping("/getById/{id}")

	public ApiResponse<VehicleDto> getById(@PathVariable("id") int id) {
		return service.getById(id);

	}

	@GetMapping("/getByIdRestTemplate/{id}")

	public String getVehicleByRestTemplate(@PathVariable int id) {
		return service.getVehicleByRestTemplate(id);

	}

	@GetMapping("/vehicle/export-pdf")

	public void exportDataToPDF() throws DocumentException, IOException {
		service.exportToPdf();
	}

	@GetMapping("/criteriagetidownerName/{ownerName}")

	public List<Vehicle> GetCriteriaIdOwnerName(@PathVariable("ownerName") String ownerName) {
		return service.FindByOwnerName(ownerName);

	}

	@GetMapping("/searchAll/{vehicleRegistrationNumber}/{ownerName}/{brand}")

	public List<Vehicle> SearchAll(@RequestParam(required = false) String vehicleRegistrationNumber,
			@RequestParam(required = false) String ownerName, @RequestParam(required = false) String brand) {
		return service.SearchAll(vehicleRegistrationNumber, ownerName, brand);

	}

}
