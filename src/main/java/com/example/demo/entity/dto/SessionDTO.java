package com.example.demo.entity.dto;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SessionDTO {
    private Long idSession;
    private Long idHealth;
    private String nama;
    private Date date;
    private Time start;
    private Integer stok;
    private Time end;
    private Date update_at;
    private Date created_ad;
    private String created_by;
}
