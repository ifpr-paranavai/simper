package com.dev.simper.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Auditable {
    @CreationTimestamp
    private Date createdDate;
    
    @UpdateTimestamp
    private Date editedDate;
}
