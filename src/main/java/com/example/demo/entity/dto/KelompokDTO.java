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
    private String nik;
    private String namaKelompok;
    private String tlp;
    private Date tglLahir;
    private String hubungan;
    private String gender;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at") 
    private Date createdAt;
}
