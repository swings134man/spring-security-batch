package com.lucas.bomkey.user;

import com.lucas.bomkey.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    private String password;
    private String userName; // Like ID ?
    private String role; // USER, ADMIN
    private String host; // Test, App1, Web1 ,,,

    //TODO: if you need etc...(phone, address ,,,)
}
