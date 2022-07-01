package com.itudy.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionEntity> ApiException(ApiException e){
        log.error("Api Exception " + e.toString());
        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(e.getError().getCode())
                .errorMessage(e.getError().getMessage())
                .build();

        return new ResponseEntity<>(apiExceptionEntity, e.getError().getStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiExceptionEntity> BindException(BindException e){
        log.error("Bind Exception " + e.getMessage());
        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
                .errorMessage(e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()).toString())
                .build();

        return new ResponseEntity<>(apiExceptionEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionEntity> MethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Validate Exception " + e.getMessage());

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
                .errorMessage(e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()).toString())
                .build();

        return new ResponseEntity<>(apiExceptionEntity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiExceptionEntity> BadCredentialsException(BadCredentialsException e){
        log.error("BadCredentialsException Exception " + e.getMessage());
        e.printStackTrace();

        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.ACCESS_DENIED_EXCEPTION.getCode())
                .errorMessage(e.getMessage())
                .build();

        return new ResponseEntity<>(apiExceptionEntity, ExceptionEnum.ACCESS_DENIED_EXCEPTION.getStatus());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ApiExceptionEntity> InternalAuthenticationServiceException(InternalAuthenticationServiceException e){
        log.error("InternalAuthenticationServiceException Exception " + e.getMessage());
        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.RUNTIME_EXCEPTION_NOT_ACTIVATED.getCode())
                .errorMessage(e.getMessage())
                .build();

        return new ResponseEntity<>(apiExceptionEntity, ExceptionEnum.RUNTIME_EXCEPTION_NOT_ACTIVATED.getStatus());
    }

//    @ExceptionHandler(UnexpectedTypeException.class)
//    public ResponseEntity<ErrorResponse> UnexpectedTypeException(UnexpectedTypeException e){
//        log.error("UnexpectedTypeException Exception " + e.getMessage());
//        ErrorResponse errorResponse = ErrorResponse.builder()
//                .message(e.getMessage())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .code("A04")
//                .build();
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionEntity> Exception(Exception e){
        log.error("Exception " + e.getMessage());
        e.printStackTrace();
        ApiExceptionEntity apiExceptionEntity = ApiExceptionEntity.builder()
                .errorCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                .errorMessage(e.getMessage())
                .build();

        return new ResponseEntity<>(apiExceptionEntity, ExceptionEnum.RUNTIME_EXCEPTION_NOT_ACTIVATED.getStatus());
    }
}

