package edu.portal.smartcommunity.lecturer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;

@Getter
@Setter
public class LecturerCreateDTO {
    @NotBlank(message = "Empty field")
    @NotNull(message = "Null field")
    @Size(max = 32, message = "Wrong size")
    private String lastName;

    private JsonNullable<String> firstName;

    @NotEmpty(message = "Empty field")
    @NotNull(message = "Null field")
    //@Pattern(regexp = "(^8|7|\\+7)((\\d{10})|(\\s\\(\\d{3}\\)\\s\\d{3}\\s\\d{2}\\s\\d{2}))\n",
    // message = "Wrong phone")
    private String phoneNumber;

    @NotEmpty(message = "Empty field")
    @NotNull(message = "Null field")
    @Email
    //@Pattern(regexp =
     //       "^[a-zA-Z0-9_+&*-] + (?:\\\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\\\.) + [a-zA-Z]{2,7}",
    //       message = "Wrong email")
    private String email;

    private JsonNullable<LocalDate> birthDate;
}
