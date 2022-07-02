package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Date;
import java.util.List;


@Entity
@Data
@Table(name = "vaksin")
public class Vaksin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaksin")
    private Long idVaksin;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_health", nullable = false)
    private  User user;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "quantity",nullable = false)
    private Long quantity;

    @UpdateTimestamp
    @Column (name = "update_at")
    private Date update_at;

    @CreationTimestamp
    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vaksin")
    private List<Session> session;

}
