package org.tsa.hms_backend.dtos;

import lombok.Data;

@Data
public class PasswordChangeDto {
    private String username;
    private String oldPassword;
    private String newPassword;
}
