package com.lucas.bomkey.domains.keys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "rsa_keys")
@Getter @Setter @ToString
public class RsaKeys {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String publicKey;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String privateKey;
    private String keyId;

    private String identifier; // 식별자
}
