package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Kelompok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kelompok")
    private Long idKelompok;

    @Column(name = "id_user")
    private Long idUser;
   
    @Column(name = "nik")
    private String nik;

    @Column(name = "username")
    private String username;

    @Column (name = "nama_kelompok")
    private String namaKelompok;

    @Column(name = "tlp")
    private String tlp;

    @Column(name = "tgl_lahir")
    private Date tglLahir;

    @Column(name = "address")
    private String address;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;

}
