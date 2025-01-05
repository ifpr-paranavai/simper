package com.dev.simper.infrastructure.configuration.database.schema;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class AuditableSchema {
    @CreationTimestamp
    private Date createdDate;
    
    @UpdateTimestamp
    private Date editedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Date editedDate) {
        this.editedDate = editedDate;
    }   
}
