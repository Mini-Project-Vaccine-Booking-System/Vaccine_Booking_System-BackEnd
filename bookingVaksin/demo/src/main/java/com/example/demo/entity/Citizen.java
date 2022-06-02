package com.example.demo.entity;



import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "citizen")
public class Citizen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id_user;
   
    @Column(name = "kk")
    private String kk;

    @Column(name = "no_hp")
    private String no_hp;

    @Column(name = "nama_citizen")
    private String nama_citizen;

    @Column(name = "gender")
    private String gender;

    @Column(name = "tgl_lahir")
    private Date tgl_lahir;

    @Column(name = "address")
    private String address;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "created_at")
    private Date created_at;

   
    
    
}

