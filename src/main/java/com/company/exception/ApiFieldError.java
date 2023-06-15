package com.company.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiFieldError extends ApiError{
	
	private List<ApiSubError> errors;
	
	public ApiFieldError(HttpStatus status) {
		super(status);
	}
	
	private void addSubError(ApiSubError subError) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(subError);
	}

	private void addValidationError(String object, String field, Object rejectedValue, String message) {
		addSubError(new FieldValidateError(object, field, rejectedValue, message));
	}

	private void addValidationError(FieldError fieldError) {
		this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}

	public void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}
	
}
