package com.RESTAPI1.RESTAPI1.OwnException;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}
