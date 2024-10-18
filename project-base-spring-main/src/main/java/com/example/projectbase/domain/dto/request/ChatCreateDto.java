package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatCreateDto {
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String tabId;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String question;

    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private String answer;
}
