package com.example.demo.entity.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class CitizenDTO {
    private long id_user;
    private String kk;
    private String no_hp;
    private String nama_citizen;
    private String gender;
    private Date tgl_lahir;
    private String address;
    private String username;
    private String role;
    private Date updated_at;
    private Date created_at;
}
