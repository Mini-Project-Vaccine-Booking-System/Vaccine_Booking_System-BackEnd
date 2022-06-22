package com.example.demo.entity.dto;

import lombok.Data;

import java.sql.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class VaksinDTO {
    private long idHealth;

    private String nama;
    private Long quantity;
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;
}
