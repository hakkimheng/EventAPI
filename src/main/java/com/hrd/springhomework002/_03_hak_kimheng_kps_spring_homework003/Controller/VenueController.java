package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Controller;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.VenueRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Response.ApiResponse;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Venue;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.VenueService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/venue")

public class VenueController {

    // inject venue service
    private final VenueService venueService;
    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    // get all venue
    @GetMapping
    @Operation(summary = "Get all venues")
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(@RequestParam(defaultValue = "1")  Integer page ,
                                                                @RequestParam(defaultValue = "10") Integer limit) {
        List<Venue> venue = venueService.getAllVenue(page, limit);
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .timestamp(LocalDateTime.now())
                .message("All venues have been successfully fetched.")
                .status(HttpStatus.OK)
                .payload(venue)
                .build();
        return ResponseEntity.ok(response);
    }
    //--End of get all venue

    // get venue by id
    @GetMapping("/{id}")
    @Operation(summary = "Get venue by ID")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable Integer id){
        Venue venue = venueService.getVenueById(id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been successfully founded.")
                .timestamp(LocalDateTime.now())
                .payload(venue)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }
    // End of get venue by id

    // post venue
    @PostMapping
    @Operation(summary = "Create Venue")
    public ResponseEntity<ApiResponse<Venue>> createVenue (@RequestBody @Valid VenueRequest venueRequest){
        Venue venue = venueService.createVenue(venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been successfully created.")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of post venue

    // update venue
    @PutMapping("/{id}")
    @Operation(summary = "Update Venue")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(@PathVariable Integer id,@RequestBody VenueRequest venueRequest){
        Venue venue = venueService.updateVenue(id, venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been successfully updated.")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of update venue

    // delete venue
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Venue")
    public ResponseEntity<ApiResponse<Venue>> deleteVenue(@PathVariable Integer id){
        venueService.deleteVenue(id);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .message("The venue has been successfully deleted.")
                .status(HttpStatus.OK)
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
