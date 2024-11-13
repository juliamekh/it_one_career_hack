package com.itonehack.smartbudget.application.service.role.dto;

import com.itonehack.smartbudget.domain.enums.ERole;
import lombok.Builder;
import lombok.Data;

/**
 * DTO for {@link com.itonehack.smartbudget.domain.model.Role}
 */
@Data
@Builder
public class RoleDTO {
    private Long id;
    private ERole name;
}