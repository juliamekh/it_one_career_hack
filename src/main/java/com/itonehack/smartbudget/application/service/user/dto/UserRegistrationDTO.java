package com.itonehack.smartbudget.application.service.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Data class representing user registration details.
 */
@Data
@Builder
public class UserRegistrationDTO {
    private String username;
    private String email;
    private String password;
}
