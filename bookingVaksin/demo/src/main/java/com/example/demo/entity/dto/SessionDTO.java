package com.example.demo.entity.dto;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import lombok.Data;

@Data
public class SessionDTO {
    private Long idSession;
    private Long idHealth;
    private String namaVaksin;
    private LocalTime start;
    private LocalTime end;
    private Date update_at;
    private Date created_ad;
    private String created_by;
}
