package com.example.demo.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "schedule")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private Long idSchedule;

    @Column(name="id_session")
    private Long idSession;

    @Column(name="start")
    private LocalDateTime start;

    @Column(name="end")
    private LocalDateTime end;
    @Column (name = "update_at")
    private Date update_at;

    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;
}
