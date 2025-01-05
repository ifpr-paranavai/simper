package com.dev.simper.entity.institution.model;

public class InstitutionModel {
    private Long id;
    private String name;
    private String domain;
    private String email;
    private String emailResponsible;
    private String cnpj;
    private String logo;
    
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
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmailResponsible() {
        return emailResponsible;
    }
    public void setEmailResponsible(String emailResponsible) {
        this.emailResponsible = emailResponsible;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
}
