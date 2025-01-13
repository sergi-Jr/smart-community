package edu.portal.smartcommunity.lecturer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LecturerUpdateDTO {
    @NotNull
    @Size(min = 1, max = 32)
    private String lastName;
}
