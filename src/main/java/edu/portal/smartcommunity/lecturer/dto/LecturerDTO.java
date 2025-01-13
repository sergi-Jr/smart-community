package edu.portal.smartcommunity.lecturer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LecturerDTO {
    private UUID id;
    private String lastName;
}
