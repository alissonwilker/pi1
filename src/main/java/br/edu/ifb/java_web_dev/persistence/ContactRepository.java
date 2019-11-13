package br.edu.ifb.java_web_dev.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifb.java_web_dev.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
}