package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table
@Data
@SuperBuilder
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kelompok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kelompok")
    private Long idKelompok;

    @ManyToOne
    @JoinColumn(name = "idUser",nullable = false)
    @JsonManagedReference
    private User user;
   
    @Column(name = "nik", unique=true,nullable = false)
    private String nik;


    @Column (name = "nama_kelompok",nullable = false)
    private String namaKelompok;

    @Column(name = "tlp",nullable = false)
    private String tlp;

    @Column(name = "tgl_lahir",nullable = false)
    private Date tglLahir;


    @Column(name = "hubungan",nullable = false)
    private String hubungan;

    @Column(name = "gender",nullable = false)
    private String gender;

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
