package com.dev.simper.infrastructure.role.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dev.simper.entity.role.gateway.RoleGateway;
import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.infrastructure.configuration.database.repository.RoleRepository;
import com.dev.simper.infrastructure.configuration.database.schema.RoleSchema;
import com.dev.simper.utils.ParseUtils;

@Service
public class RoleDatabaseGateway implements RoleGateway {

    private final RoleRepository roleRepository;

    public RoleDatabaseGateway(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleModel save(RoleModel model) {
        return ParseUtils.parse(
            roleRepository.saveAndFlush(ParseUtils.parse(model, RoleSchema.class)), 
            RoleModel.class
            );
    }

    @Override
    public Optional<RoleModel> findById(Long id) {
        return roleRepository.findById(id)
            .map(schema -> ParseUtils.parse(schema, RoleModel.class));
    }

    @Override
    public List<RoleModel> findAll() {
        return ParseUtils.parse(
            roleRepository.findAll(), 
            RoleModel.class
            );
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public RoleModel update(RoleModel model) {
        return ParseUtils.parse(
            roleRepository.saveAndFlush(ParseUtils.parse(model, RoleSchema.class)), 
            RoleModel.class
            );
    }
}
