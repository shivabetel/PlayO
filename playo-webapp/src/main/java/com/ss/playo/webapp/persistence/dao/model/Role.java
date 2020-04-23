package com.ss.playo.webapp.persistence.dao.model;


import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(name = "ROLE_NAME", nullable = false, unique = true)
    private String name;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ROLES_PRIVILEGES",
    joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")},
    inverseJoinColumns = {@JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "PRIVILEGE_ID")})
    private Set<Privilege> privilegeSet;

    public Role() {
    }

    public Role(String name, Set<Privilege> privilegeSet) {
        this.name = name;
        this.privilegeSet = privilegeSet;
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

    public Set<Privilege> getPrivilegeSet() {
        return privilegeSet;
    }

    public void setPrivilegeSet(Set<Privilege> privilegeSet) {
        this.privilegeSet = privilegeSet;
    }
}
