package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ChangePasswordDto {
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String userId;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String oldPassword;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$", message = ErrorMessage.INVALID_FORMAT_PASSWORD)
    private String newPassword;
}
