package com.example.demo.entity;




import java.util    .Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "user", uniqueConstraints={
    @UniqueConstraint(columnNames = {"email", "nik"})})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idUser")
    private Long idUser;

   
    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "nik")
    private String nik;

    @Column(name = "no_hp")
    private String noHp;

    @Column(name = "nama")
    private String nama;

    @Column(name = "gender")
    private String gender;

    @Column(name = "tgl_lahir")
    private Date tglLahir;

    @Column(name = "image")
    private String image;

    @Column(name = "address")
    private String address;

    @Column(name = "kota")
    private String kota;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    
    @Column(name= "updated_by")
    private String updatedBy;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Kelompok> kelompok;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Vaksin> vaksin;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Session> session;
    
}

