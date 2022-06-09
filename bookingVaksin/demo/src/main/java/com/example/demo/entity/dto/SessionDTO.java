package com.example.demo.entity.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class SessionDTO {
    private Long idSession;
    private Long idHealth;
    private LocalDateTime start;
    private LocalDateTime end;
    private Date update_at;
    private Date created_ad;
    private String created_by;
}
