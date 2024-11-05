package com.lucas.bomkey.client;

import com.lucas.bomkey.config.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client_info")
@Getter
@Setter
@ToString
public class ClientInfo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String clientDesc;
    private String clientUrl;
    private String redirectUrl;

}
