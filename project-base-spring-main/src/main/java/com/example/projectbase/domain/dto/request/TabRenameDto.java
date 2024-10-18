package com.example.projectbase.domain.dto.request;

import com.example.projectbase.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TabRenameDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String id;

    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String name;
}
