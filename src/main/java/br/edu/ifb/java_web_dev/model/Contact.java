package br.edu.ifb.java_web_dev.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(updatable = true, nullable = false, unique = true)
    private String name;

    public Contact() {
        
    }

    public Contact(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof Contact) {
            Contact c = (Contact) obj;
            result = c.getId() == this.getId();
        }

        return result;
    }

    @Override
    public int hashCode() {
        return getId() ^ 7;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}