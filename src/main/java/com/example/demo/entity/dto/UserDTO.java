package com.example.demo.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.demo.entity.RoleEnum;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO  implements Serializable {
    private static final long serialVersionUID =  1L;
    private long idUser;
    private String email;
    private String nik;
    private String noHp;
    private String nama;
    private String gender;
    private String image;
    private Date tglLahir;
    private String address;
    // private String username;
    private String password;
    private String currentPassword;
    private String newPassword;
    private String kota;
    private List<RoleEnum> roles;
    private Date updated_at;
    private Date created_at;
}
