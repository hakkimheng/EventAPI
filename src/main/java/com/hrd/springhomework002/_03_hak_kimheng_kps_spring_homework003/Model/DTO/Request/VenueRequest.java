package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue Name Can't Be Empty")
    private String venueName;
    @NotBlank(message = "Location Can't Be Empty")
    private String venueLocation;
}
