package com.example.demo.entity;



import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_health", nullable = false)
    private User user;

    @Column(name="date")
    private Date date;

    @Column(name="start")
    private Time start;

    @Column(name="stok")
    private Integer stok;

    @ManyToOne
    @JoinColumn(name = "nama_vaksin", nullable = false)
    private Vaksin vaksin;

    @Column(name="end")
    private Time end;
    
    @Column (name = "update_at")
    private Date update_at;

    @CreationTimestamp
    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "session")
    private List<Booking> booking;
    
}

