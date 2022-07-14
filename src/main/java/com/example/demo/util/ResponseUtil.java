package com.example.demo.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.constant.AppConstant;
import com.example.demo.entity.base.ApiResponse;

public class ResponseUtil {
    private ResponseUtil() {}

    public static <T> ResponseEntity<Object> build(String message, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(message, data), httpStatus);
    }

    private static <T> ApiResponse<T> build(String message, T data) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .data(data)
                .build();
    }
    
    public static <T> ResponseEntity<Object> build(String message,AppConstant.ResponseCode responseCode, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(build(message,responseCode, data), httpStatus);
    }

    private static <T> ApiResponse<T> build(String message,AppConstant.ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .message(message)
                .responseCode(responseCode.name())
                .data(data)
                .build();
    }
}
