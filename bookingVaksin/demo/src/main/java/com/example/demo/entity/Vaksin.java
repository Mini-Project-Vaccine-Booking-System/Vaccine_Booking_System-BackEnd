package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;


@Entity
@Data
@Table(name = "vaksin")
public class Vaksin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaksin")
    private Long idVaksin;

    @Column (name = "id_health")
    private  Long idHealth;

    @Column(name = "nama")
    private String nama;

    @Column(name = "quantity")
    private Long quantity;

    @Column (name = "update_at")
    private Date update_at;

    @CreationTimestamp
    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;
}
