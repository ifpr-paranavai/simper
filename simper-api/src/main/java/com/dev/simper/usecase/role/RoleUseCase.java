package com.dev.simper.usecase.role;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.dev.simper.entity.exception.ResourceNotFoundException;
import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.usecase.role.dto.IRoleDto;
import com.dev.simper.utils.ParseUtils;

import java.util.List;
import java.util.Locale;

@Service
public class RoleUseCase implements IRoleUseCase {

    private final RoleGateway roleGateway;
    private final MessageSource messageSource;

    public RoleUseCase(RoleGateway roleGateway, MessageSource messageSource) {
        this.roleGateway = roleGateway;
        this.messageSource = messageSource;
    }

    @Override
    public List<RoleModel> findAll() {
        return roleGateway.findAll();
    }

    @Override
    public RoleModel findById(Long id) {
        return roleGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("error.role.notfound", new Object[] { id }, Locale.getDefault())));
    }

    @Override
    public RoleModel save(IRoleDto dto) {
        return roleGateway.save(ParseUtils.parse(dto, RoleModel.class));
    }

    @Override
    public RoleModel update(IRoleDto dto) {
        return roleGateway.update(ParseUtils.parse(dto, RoleModel.class));
    }

    @Override
    public void delete(Long id) {
        roleGateway.delete(id);
    }
}
