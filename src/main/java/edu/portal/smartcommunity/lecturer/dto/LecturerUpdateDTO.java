package edu.portal.smartcommunity.lecturer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Date;

@Getter
@Setter
public class LecturerUpdateDTO {
    @NotBlank
    @NotNull
    @Size(max = 32)
    private String lastName;

    private JsonNullable<String> firstName;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "(^8|7|\\+7)((\\d{10})|(\\s\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}))\n")
    private String phoneNumber;

    @NotEmpty
    @NotNull
    @Email
    @Pattern(regexp =
            "^[a-zA-Z0-9_+&*-] + (?:\\\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\\\.) + [a-zA-Z]{2,7}")
    private String email;

    private JsonNullable<Date> birthDay;
}
