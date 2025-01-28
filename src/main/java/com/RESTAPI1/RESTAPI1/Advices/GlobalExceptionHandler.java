package com.RESTAPI1.RESTAPI1.Advices;

import com.RESTAPI1.RESTAPI1.OwnException.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<APIresponse<?>> getNosuchexcp(ResourceNotFound exception){
        APIError apiError=APIError.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return buildapiresponse(apiError);
    }



    @ExceptionHandler(Exception.class)
    public  ResponseEntity<APIresponse<?>> getNosuchexcp(Exception exception){
        APIError apiError=APIError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(exception.getMessage()).build();
        return buildapiresponse(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<APIresponse<?>> getNosuchexcp(MethodArgumentNotValidException exception){
       List<String> errors= exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());


        APIError apiError=APIError.builder().status(HttpStatus.BAD_REQUEST).message("Input Validation Failed").subErrors(errors).build();
        return buildapiresponse(apiError);

    }

    private ResponseEntity<APIresponse<?>> buildapiresponse(APIError apiError) {
        return new ResponseEntity<>(new APIresponse<>(apiError),apiError.getStatus());
    }
}
