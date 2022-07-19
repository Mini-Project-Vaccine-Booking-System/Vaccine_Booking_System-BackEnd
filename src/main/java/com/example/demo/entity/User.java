package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@Data
@SuperBuilder
@Builder
@Table(name = "user")
// uniqueConstraints={
//     @UniqueConstraint(columnNames = {"email", "nik"})})
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idUser")
    private Long idUser;

    @Column(name = "username", nullable = false)
    @Email(message = "EMAIL IS NOT VALID")
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    private String username;
    
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "nik", unique = true)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Kelompok> kelompok;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Vaksin> vaksin;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Session> session;
    
}

