package com.ss.playo.webapp.persistence.dao.model;


import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.*;

@Entity
public class Privilege implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRIVILEGE_ID")
    private Long id;

    @Column(name = "PRIVILEGE_NAME", nullable = false, unique = true)
    private String name;

    private String description;

    public Privilege() {
    }

    public Privilege(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
