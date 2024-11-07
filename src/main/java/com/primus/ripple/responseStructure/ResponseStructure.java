package com.primus.ripple.responseStructure;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private Integer statusCode;
	private String message;
	private T data;

}
