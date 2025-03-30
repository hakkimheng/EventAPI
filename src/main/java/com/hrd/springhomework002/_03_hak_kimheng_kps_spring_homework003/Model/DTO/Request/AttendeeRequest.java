package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee Name Can't be Empty")
    private String attendeeName;
    @Email(message = "Wrong format follow: \"example@gmail.com\"")
    private String attendeeEmail;
}
