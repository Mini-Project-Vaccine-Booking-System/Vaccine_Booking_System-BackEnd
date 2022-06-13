package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table
@Data
public class Kelompok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kelompok")
    private Long idKelompok;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
   
    @Column(name = "nik")
    private String nik;

    @Column(name = "username",nullable = false)
    private String username;

    @Column (name = "nama_kelompok",nullable = false)
    private String namaKelompok;

    @Column(name = "tlp",nullable = false)
    private String tlp;

    @Column(name = "tgl_lahir",nullable = false)
    private Date tglLahir;

    @Column(name = "address",nullable = false)
    private String address;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "kelompok")
    private List<Booking> booking;
}
