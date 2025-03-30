package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Controller;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Attendee;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.AttendeeRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Response.ApiResponse;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.AttendeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendee")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }
    // get all attendees
    @GetMapping
    @Operation(summary = "Get all Attendees")
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees (@RequestParam(defaultValue = "1") Integer page,
                                                                        @RequestParam(defaultValue = "10") Integer limit){

        List<Attendee> attendee = attendeeService.getAllAttendees(page,limit);
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .message("All Attendees have been successfully fetched.")
                .status(HttpStatus.OK)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of get all attendees

    // get attendee by id
    @GetMapping("/{id}")
    @Operation(summary = "Get Attendee by Id")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable Integer id){
        Attendee attendee = attendeeService.getAttendeeById(id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully founded.")
                .status(HttpStatus.OK)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // post attendee
    @PostMapping
    @Operation(summary = "Create Attendee")
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@RequestBody @Valid AttendeeRequest attendeeRequest){
        Attendee attendee = attendeeService.createAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully added.")
                .status(HttpStatus.CREATED)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of post attendee

    // update attendee
    @PutMapping("/{id}")
    @Operation(summary = "Update Attendee")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(@RequestBody @Valid AttendeeRequest attendeeRequest,
                                                                @PathVariable Integer id){
        Attendee attendee = attendeeService.updateAttendee(attendeeRequest,id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully updated.")
                .status(HttpStatus.OK)
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of update attendee

    // delete attendee
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Attendee")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendee(@PathVariable Integer id){
        attendeeService.deleteAttendee(id);
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .message("The attendee has been successfully updated.")
                .status(HttpStatus.OK)
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


}
