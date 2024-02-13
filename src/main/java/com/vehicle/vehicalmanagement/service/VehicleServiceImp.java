package com.vehicle.vehicalmanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vehicle.vehicalmanagement.bean.ExportPdf;
import com.vehicle.vehicalmanagement.bean.Vehicle;
import com.vehicle.vehicalmanagement.dto.VehicleDto;
import com.vehicle.vehicalmanagement.exception.ResourceNotFoundException;
import com.vehicle.vehicalmanagement.mapper.VehicleMapper;
import com.vehicle.vehicalmanagement.payload.ApiResponse;
import com.vehicle.vehicalmanagement.repository.VehicleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Component
public class VehicleServiceImp implements VehicleService {

	@Autowired
	private VehicleRepository repository;

	@Autowired
	private VehicleMapper vehicleMapper;

	@Autowired
	private RestTemplate resttemplate;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private EntityManager manager;

	@Override
	@Cacheable(cacheNames = "getall", key = "#pageNumber,pageSize")
	public ApiResponse<VehicleDto> getAllVehicles(int pageNumber, int pageSize) {
		try {

			Pageable page = PageRequest.of(pageNumber, pageSize);
			Page<Vehicle> vehicle = repository.findAll(page);
			List<Vehicle> listvehicle = vehicle.getContent();
			return new ApiResponse<VehicleDto>(null, null, null, "Vehicle List Page", HttpStatus.OK, false);
		} catch (Exception e) {

			return new ApiResponse<VehicleDto>(null, null, null, "Internal Server Error", HttpStatus.NOT_FOUND, true);
		}
	}

	@Override
	//@CachePut(cacheNames = "Addcach", key = "#vehicleDto")
	public ApiResponse<VehicleDto> addVehicle(VehicleDto vehicleDto) {
		try {
			Vehicle vehicle = vehicleMapper.mapVehicleDtoToVehicle(vehicleDto);

			Vehicle savedVehicle = repository.save(vehicle);

			VehicleDto savedVehicleDto = vehicleMapper.mapVehicleToVehicleDto(savedVehicle);

			return new ApiResponse<VehicleDto>(vehicleDto, null, null, "Vehicle added successfully", HttpStatus.OK,
					false);
		} catch (Exception e) {
			return new ApiResponse<VehicleDto>(null, null, null, "Internal Server Error", HttpStatus.NOT_FOUND, true);
		}
	}

	@Override
	//@CacheEvict(cacheNames = "delete", key = "#id")
	public ApiResponse<VehicleDto> deleteVehicleDto(int id) {
//	   Vehicle vehicle =vehicleMapper.mapVehicleDtoToVehicle(id);
//	    vehicleMapper.mapVehicleToVehicleDto(vehicle);

		try {
			repository.deleteById(id);
			return new ApiResponse<VehicleDto>(null, null, null, "Vehicle Deleted successfully", HttpStatus.OK, false);
		} catch (Exception e) {
			return new ApiResponse<VehicleDto>(null, null, null, "Internal Server Error", HttpStatus.NOT_FOUND, true);
		}

	}

	@Override
	//@CachePut(cacheNames = "update", key = "#vehicledto.id")
	public ApiResponse<VehicleDto> UpdateVehicle(VehicleDto vehicledto, int id) {
		try {
			Vehicle existingVehicle1 = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Id", id));
			existingVehicle1.setOwnerName(vehicledto.getOwnerName());
			existingVehicle1.setBrand(vehicledto.getBrand());
			existingVehicle1.setCreationTime(vehicledto.getCreationTime());
			existingVehicle1.setModifiedBy(vehicledto.getModifiedBy());
			existingVehicle1.setModifiedTime(vehicledto.getModifiedTime());
			existingVehicle1.setCreatedBy(vehicledto.getCreatedBy());

			VehicleDto vehicleDtoObj = vehicleMapper.mapVehicleToVehicleDto(repository.save(existingVehicle1));

			return new ApiResponse<VehicleDto>(vehicleDtoObj, null, null, "Vehicle Updated successfully", HttpStatus.OK,
					false);

		} catch (Exception e) {
			return new ApiResponse<VehicleDto>(null, null, null, "Internal Server Error", HttpStatus.NOT_FOUND, true);
		}

	}

	@Override
	@Cacheable(cacheNames = "getid")//, key = "#id")
	public ApiResponse<VehicleDto> getById(int id) {

		try {
			Vehicle vehicle = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Id", id));
			VehicleDto dto = vehicleMapper.mapVehicleToVehicleDto(vehicle);
			return new ApiResponse<VehicleDto>(dto, null, null, "Rest Template data fetch successfully", HttpStatus.OK,
					false);

		} catch (Exception e) {

			return new ApiResponse<VehicleDto>(null, null, null, "Internal Server Error", HttpStatus.NOT_FOUND, true);

		}

	}

	@Override
	public String getVehicleByRestTemplate(int id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> templateData = resttemplate.exchange("https://jsonplaceholder.typicode.com/todos/1",
					HttpMethod.GET, entity, String.class, headers);

			return templateData.getBody();

		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public void exportToPdf() {
		try {
			response.setContentType("application/pdf");
			String headerkey = "Content-Disposition";
			String headervalue = "attachment; filename=Vehicle-list" + ".pdf";
			response.setHeader(headerkey, headervalue);

			List<Vehicle> vehicleList = repository.findAll();

			ExportPdf pdfGenerator = new ExportPdf();
			pdfGenerator.generatePdf(vehicleList, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	

	

	@Override
	public List<Vehicle> FindByOwnerName( String ownerName) {
		CriteriaBuilder builder=manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle>criteriaQuery=builder.createQuery(Vehicle.class);
		Root<Vehicle>root=criteriaQuery.from(Vehicle.class);
		criteriaQuery.select(root).where(builder.between(root.get("id"), 1, 3));
		Predicate predicate=builder.like(root.get("ownerName"),"%"+ownerName+"%");
		
		
		//Predicate predicate1=builder.equal(root.get("ownerName"),ownerName);
		criteriaQuery.where(predicate);
		//criteriaQuery.where(predicate1);
		TypedQuery<Vehicle>query=manager.createQuery(criteriaQuery);
		
		
		return query.getResultList();
	}

	@Override
	public List<Vehicle> SearchAll(String vehicleRegistrationNumber, String ownerName, String brand) {
		CriteriaBuilder builder=manager.getCriteriaBuilder();
		CriteriaQuery<Vehicle>criteriaQuery=builder.createQuery(Vehicle.class);
		Root<Vehicle>root=criteriaQuery.from(Vehicle.class);
		
		List<Predicate>predicates=new ArrayList<>();
		if(vehicleRegistrationNumber !=null && !vehicleRegistrationNumber.isEmpty()) {
			predicates.add(builder.like(root.get("vehicleRegistrationNumber"), "%"+vehicleRegistrationNumber+"%"));
		}
		else if (ownerName !=null && !ownerName.isEmpty()) {
			predicates.add(builder.like(root.get("ownerName"), "%"+ownerName+"%"));
		}
		else if (brand !=null && !brand.isEmpty()) {
			predicates.add(builder.like(root.get("brand"), "%"+brand+"%"));
		}
		criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
		return manager.createQuery(criteriaQuery).getResultList();
	}

}