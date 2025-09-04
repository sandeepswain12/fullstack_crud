package com.crud.exception;

import com.crud.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.info("exception handler invoker");
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setMessage(e.getMessage());
        apiResponseMessage.setSuccess(true);
        apiResponseMessage.setStatus(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.NOT_FOUND);
    }

}
