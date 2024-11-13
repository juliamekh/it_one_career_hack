package com.itonehack.smartbudget.infrastructure.jpa.role;

import com.itonehack.smartbudget.domain.enums.ERole;
import com.itonehack.smartbudget.domain.model.Role;
import com.itonehack.smartbudget.domain.ports.out.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of {@link RoleRepositoryPort}
 */
@Component
@RequiredArgsConstructor
public class JpaRoleRepositoryAdapter implements RoleRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(JpaRoleRepositoryAdapter.class);

    private final JpaRoleRepository jpaRoleRepository;

    @Override
    public Optional<Role> findByName(ERole name) {
        logger.info("Finding role by name: {}", name);
        return jpaRoleRepository.findByName(name);
    }
}
