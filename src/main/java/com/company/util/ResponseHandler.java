package com.company.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.exception.ApiError;

public class ResponseHandler {

	public static ResponseEntity<?> success(String message, HttpStatus status, Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("status", status.value());
		map.put("data", responseObj);

		return new ResponseEntity<>(map, status);
	}
	public static ResponseEntity<Object> error(ApiError  responseObj) {
        return new ResponseEntity<>(responseObj, responseObj.getStatus());
    }
}
