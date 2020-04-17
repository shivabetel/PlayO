package com.ss.playo.webapp.persistence.dao.model;


import com.ss.playo.common.interfaces.IEntity;

import javax.persistence.*;
import java.util.Date;







@Entity
@NamedQuery(name = "Lookup.details", query = "select lookupDetails from Lookup lookupDetails")
public class Lookup implements IEntity {


    @Id
    @Column(name = "LOOKUP_KEY", nullable = false)
    private String key;

    @Column(name="LOOKUP_VALUE", nullable = false)
    private String value;

    @Column(name="CREATION_DATE")
    private Date creationDate;

    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Date lastUpdatedDate;

    @Column(name = "CREATED_BY", nullable = true)
    private String createdBy;

    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private String lastUpdatedBy;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
