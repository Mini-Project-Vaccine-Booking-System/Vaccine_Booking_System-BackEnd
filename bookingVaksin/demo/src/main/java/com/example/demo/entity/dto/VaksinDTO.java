package com.example.demo.entity.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class VaksinDTO {
    private long idVaksin;
    private long idHealth;
    private String nama;
    private Long quantity;
    private Date update_at;
    private Date created_ad;
    private String created_by;
}
