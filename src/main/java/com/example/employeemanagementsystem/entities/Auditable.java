package com.example.employeemanagementsystem.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date creationDate;
    @Temporal(TIMESTAMP)
    @LastModifiedDate
    Date lastModifiedDate;
// Getters and Setters

    public Auditable(Date creationDate, Date lastModifiedDate) {
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Auditable() {
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}