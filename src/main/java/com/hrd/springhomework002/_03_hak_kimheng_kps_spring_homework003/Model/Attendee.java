package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {

    private Integer attendeeId;
    private String attendeeName;
    private String attendeeEmail;

}
