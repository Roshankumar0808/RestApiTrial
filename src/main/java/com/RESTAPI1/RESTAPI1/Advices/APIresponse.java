package com.RESTAPI1.RESTAPI1.Advices;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class APIresponse <T>{
    private T data;
    private APIError error;
    @JsonFormat(pattern ="hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timeStamp;

    public APIresponse(T data) {
        this();
        this.data = data;
    }

    public APIresponse() {

        this.timeStamp = LocalDateTime.now();
    }

    public APIresponse(APIError error) {
        this();
        this.error = error;
    }
}
