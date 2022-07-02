package com.example.mapstest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class ResponseAPI {
    private String message;
    private Integer statusCode;

}
