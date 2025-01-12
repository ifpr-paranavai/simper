package com.dev.simper.usecase.role.implementation;

import org.springframework.context.MessageSource;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.contract.IGetRoleUseCase;

import java.util.Locale;

public class GetRoleUseCase implements IGetRoleUseCase {

    private final RoleGateway roleGateway;
    private final MessageSource messageSource;

    public GetRoleUseCase(RoleGateway roleGateway, MessageSource messageSource) {
        this.roleGateway = roleGateway;
        this.messageSource = messageSource;
    }

    @Override
    public RoleModel execute(Long id) {
        return roleGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.role.notfound", new Object[] { id }, Locale.getDefault())));
    }
}
