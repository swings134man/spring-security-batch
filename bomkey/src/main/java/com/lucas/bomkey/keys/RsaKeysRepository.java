package com.lucas.bomkey.keys;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RsaKeysRepository extends JpaRepository<RsaKeys, Long> {
    Optional<RsaKeys> findByIdentifier(String identifier);
}
