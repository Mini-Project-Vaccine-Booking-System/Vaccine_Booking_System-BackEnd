package com.example.demo.entity.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KelompokDTO {

    
    private Long idUser;
   
    @Column( nullable = false)
    private String nik;

    @Column(name = "username")
    private String username;

    @Column ( nullable = false)
    private String namaKelompok;

    @Column( nullable = false)
    private String tlp;

    @Column(name = "tgl_lahir")
    private Date tglLahir;

    @Column( nullable = false)
    private String address;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
    private Date createdAt;
}
