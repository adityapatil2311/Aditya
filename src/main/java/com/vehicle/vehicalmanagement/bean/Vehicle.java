package com.vehicle.vehicalmanagement.bean;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehicle")
@Data
@Audited
public class Vehicle {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique = true)
	
	private int id;
	@Column(unique = true )
	private String  vehicleRegistrationNumber ;
	private String	ownerName;
	private String brand;
	
	private LocalDateTime  registrationExpiresDate;
	private boolean isActive ;
	private String createdBy; 
	@CreationTimestamp
	private LocalDateTime creationTime ; 
	private String modifiedBy;
	@UpdateTimestamp
	private LocalDateTime modifiedTime ;
	
	

	public Vehicle() {
		super();
	}
	public Vehicle(int id, String vehicleRegistrationNumber, String ownerName, String brand,
			LocalDateTime registrationExpiresDate, boolean isActive, String createdBy, LocalDateTime creationTime,
			String modifiedBy, LocalDateTime modifiedTime) {
		super();
		this.id = id;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.ownerName = ownerName;
		this.brand = brand;
		this.registrationExpiresDate = registrationExpiresDate;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.creationTime = creationTime;
		this.modifiedBy = modifiedBy;
		this.modifiedTime = modifiedTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}
	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public LocalDateTime getRegistrationExpiresDate() {
		return registrationExpiresDate;
	}
	public void setRegistrationExpiresDate(LocalDateTime registrationExpiresDate) {
		this.registrationExpiresDate = registrationExpiresDate;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
