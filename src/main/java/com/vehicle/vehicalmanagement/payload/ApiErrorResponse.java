package com.vehicle.vehicalmanagement.payload;

import org.springframework.http.HttpStatusCode;

public class ApiErrorResponse {


	private String message;
    private boolean success;
    private HttpStatusCode statusCode;
    
	public ApiErrorResponse(String message, boolean success, HttpStatusCode statusCode) {
		this.message = message;
		this.success = success;
		this.statusCode = statusCode;
	}
    
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HttpStatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatusCode statusCode) {
		this.statusCode = statusCode;
	}


}
