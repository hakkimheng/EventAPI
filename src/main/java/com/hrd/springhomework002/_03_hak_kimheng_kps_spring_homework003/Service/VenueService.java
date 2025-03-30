package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.VenueRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Venue;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface VenueService {
    public List<Venue> getAllVenue(Integer page , Integer limit);

    Venue createVenue(VenueRequest venueRequest);

    Venue getVenueById(@Valid @Min(1) Integer id);

    Venue updateVenue(Integer venueId, VenueRequest venueRequest);

    void deleteVenue(Integer id);
}
