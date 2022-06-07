package com.example.demo.entity.dto;

import lombok.Data;

import java.sql.Date;


@Data
public class StokVaksinDTO {
    private long id_stock;
    private long health_id;
    private Integer quantity;
    private String jenis_vaksin;
    private Date update_at;
    private Date created_ad;
    private String created_by;
}
