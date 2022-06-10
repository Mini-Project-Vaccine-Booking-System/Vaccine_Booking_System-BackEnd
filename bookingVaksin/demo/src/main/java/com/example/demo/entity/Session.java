package com.example.demo.entity;



import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_session")
    private Long idSession;

    @Column(name="id_Health")
    private Long idHealth;

    @Column(name="start")
    private LocalDateTime start;

    @Column(name="nama")
    private String nama;

    @Column(name="end")
    private LocalDateTime end;
    
    @Column (name = "update_at")
    private Date update_at;

    @CreationTimestamp
    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;
}

