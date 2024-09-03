package com.lucas.securityjwt.bank.repository.jpa_interface;

import com.lucas.securityjwt.bank.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
}
