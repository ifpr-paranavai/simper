package com.dev.simper.entity.user.model;

import com.dev.simper.entity.role.model.RoleModel;

public class UserRolesModel {
    private Long id;
    private RoleModel role;
    private UserModel user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public RoleModel getRole() {
        return role;
    }
    public void setRole(RoleModel role) {
        this.role = role;
    }
    public UserModel getUser() {
        return user;
    }
    public void setUser(UserModel user) {
        this.user = user;
    }    
}
