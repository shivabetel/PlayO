package com.ss.playo.webapp.persistence.dao.model;


import com.ss.playo.common.interfaces.IEntity;
import org.hibernate.type.VersionType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements IEntity<String> {

    @Id
    @Column(name = "EMAIL_ID", nullable = false)
    private String emailId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    private String lastName;

   @Column(name = "password", nullable = false)
    private String password;

   @Column(name = "enabled")
   private boolean enabled;

   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JoinTable(name = "USER_ROLES",
   joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "EMAIL_ID")},
   inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
   private Set<Role> roleSet;

   @Version
   private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public User() {
        super();
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String getId() {
        return emailId;
    }


}
