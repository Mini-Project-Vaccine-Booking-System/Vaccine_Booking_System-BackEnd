package com.example.demo.entity;



import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    @JoinColumn(name = "id_health", nullable = false)
    private User user;

    @Column(name="start")
    private LocalDateTime start;

    @ManyToOne
    @JoinColumn(name = "nama_vaksin", nullable = false)
    private Vaksin vaksin;

    @Column(name="end")
    private LocalDateTime end;
    
    @Column (name = "update_at")
    private Date update_at;

    @CreationTimestamp
    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "session")
    private List<Schedule> schedule;
}

