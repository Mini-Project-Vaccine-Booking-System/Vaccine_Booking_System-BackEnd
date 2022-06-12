package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

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
    @JoinColumn(name = "id_health", nullable = false)
    private  User user;

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

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vaksin")
    private List<Session> session;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vaksin")
    private List<Booking> booking;
}
