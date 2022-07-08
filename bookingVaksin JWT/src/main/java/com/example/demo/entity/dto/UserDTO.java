package com.example.demo.entity.dto;

import java.util.Date;

import lombok.*;

@Data
public class UserDTO {
    private long idUser;
    private String email;
    private String nik;
    private String noHp;
    private String nama;
    private String gender;
    private String image;
    private Date tglLahir;
    private String address;
    private String username;
    private String password;
    private String kota;
    private String role;
    private Date updated_at;
    private Date created_at;
}
