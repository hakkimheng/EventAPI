package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event Name Can't be Empty")
    private String eventName;
    private Date eventDate;
    private Integer venueId;
    private List<Integer> attendeeId;
}
