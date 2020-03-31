package com.ss.playo.webapp.persistence.dao.model;


import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements IEntity {

    @Id
    @Column(name = "EMAIL_ID", nullable = false)
    private String emailId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    private String lastName;

   @Column(name = "password", nullable = false)
    private String password;

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
}
