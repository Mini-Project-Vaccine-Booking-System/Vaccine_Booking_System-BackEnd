package com.example.demo.entity.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingDTO {
    
    private Long idBooking;
    private Long idKelompok;
    private Long idSession;
    private Date created_at;
    private Date updated_at;
}
