package com.example.demo.entity;




import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id_user;

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
    private String no_hp;

    @Column(name = "nama")
    private String nama;

    @Column(name = "gender")
    private String gender;

    @Column(name = "tgl_lahir")
    private Date tgl_lahir;

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

   
    
}

