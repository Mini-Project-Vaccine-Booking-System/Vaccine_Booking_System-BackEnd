package com.example.demo.entity.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleDTO {
    private Long idSchedule;
    private Long idSession;
    private LocalDateTime start;
    private LocalDateTime end;
    private Date update_at;
    private Date created_ad;
    private String created_by;
}
