package com.vehicle.vehicalmanagement.exception;

public class DuplicateFieldException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DuplicateFieldException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s with %s : '%s' already exist", resourceName, fieldName, fieldValue ));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
