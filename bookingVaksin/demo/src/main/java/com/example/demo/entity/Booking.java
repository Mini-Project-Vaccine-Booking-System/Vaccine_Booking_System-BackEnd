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
@Data
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
    private Long id_booking;

    @Column(name = "id_session")
    private Long id_session;

    @Column(name = "no_tiket")
    private Long no_tiket;

    @Column(name = "nama_schedule")
    private Integer nama_schedule;

    @Column(name = "time")
    private Date time;

    @Column(name = "jenis_vaksin")
    private Date jenis_vaksin;

    @Column(name = "petugas")
    private String petugas;

    @Column(name = "tempat_facility")
    private String tempat_facility;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;
    
}
