package com.itonehack.smartbudget.domain.ports.in;

import com.itonehack.smartbudget.domain.enums.ERole;
import com.itonehack.smartbudget.domain.model.Role;

/**
 * Interface for interacting with role data in the application.
 */
public interface RoleService {

    /**
     * Find a role by its name.
     *
     * @param name The name of the role to find.
     * @return The role found, or null if not found.
     */
    Role findByName(ERole name);
}
