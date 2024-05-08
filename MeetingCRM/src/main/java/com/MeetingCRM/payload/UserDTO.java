package com.MeetingCRM.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String mobileNumber;
    private LocalDate dateOfRegistration;
}
