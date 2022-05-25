package com.kushal.blog.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kushal.blog.payloads.ApiResponse;

//To handle exceptions in all the controllers
@RestControllerAdvice
public class GlobalExceptionHandler {
	//Handling exception
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
			ApiResponse apiResponse=new ApiResponse(exception.getMessage(),false);
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(apiResponse);
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<Map<String,String>> handleMethodArgNotFoundException(MethodArgumentNotValidException exception){
			Map<String,String> response=new HashMap<>();
			exception.getBindingResult().getAllErrors().forEach((error)->{
				String fieldName=((FieldError)error).getField();
				String message=error.getDefaultMessage();
				response.put(fieldName, message);
			});
			return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		}
		
		
		@ExceptionHandler(ApiException.class)
		public ResponseEntity<ApiResponse> handleApiException(ApiException exception){
			String message=exception.getMessage();
			ApiResponse apiResponse=new ApiResponse(exception.getMessage(),false);
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(apiResponse);
		}
		
		
}
