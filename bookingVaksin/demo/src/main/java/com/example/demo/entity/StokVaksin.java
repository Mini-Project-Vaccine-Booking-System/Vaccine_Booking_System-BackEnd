package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@Table(name = "stock_vaksin")
public class StokVaksin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock")
    private Long id_stock;

    @Column (name = "health_id")
    private  Long health_id;

    @Column(name = "quatity")
    private Integer quantity;

    @Column (name = "jenis_vaksin")
    private String jenis_vaksin;

    @Column (name = "update_at")
    private Date update_at;

    @Column (name = "created_at")
    private Date created_ad;

    @Column (name = "created_by")
    private String created_by;
}
