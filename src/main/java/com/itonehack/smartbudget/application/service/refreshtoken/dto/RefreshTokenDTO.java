package com.itonehack.smartbudget.application.service.refreshtoken.dto;

import com.itonehack.smartbudget.application.service.user.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * DTO for {@link com.itonehack.smartbudget.domain.model.RefreshToken}
 */
@Data
@Builder
public class RefreshTokenDTO {
    private Long id;
    private Instant expiryDate;
    private String token;
    private UserDTO userDTO;
}