package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
   
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

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "kelompok")
    private List<Booking> booking;
}
