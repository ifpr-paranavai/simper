package com.dev.simper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "institutions")
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "domain", nullable = false)
    private String domain;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "email_responsible", nullable = false, unique = true)
    private String emailResponsible;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Lob
    @Column(name = "logo")
    private String logo;
}
