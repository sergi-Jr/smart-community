package edu.portal.smartcommunity.lecturer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class LecturerDTO {
    private UUID id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private Date birthDay;
}
