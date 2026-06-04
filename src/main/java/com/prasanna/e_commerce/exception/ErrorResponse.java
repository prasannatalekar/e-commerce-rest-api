package com.prasanna.e_commerce.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

	private LocalDateTime localDateTime;
	private int status;
	private String message;
	private String path;
	
}
