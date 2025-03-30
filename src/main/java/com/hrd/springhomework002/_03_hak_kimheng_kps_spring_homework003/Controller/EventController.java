package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Controller;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.EventRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Response.ApiResponse;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Events;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    //get all events
    @GetMapping
    @Operation(summary = "Get all Events")
    public ResponseEntity<ApiResponse<List<Events>>> getAllEvents(@RequestParam(defaultValue = "1") Integer page , @RequestParam(defaultValue = "10") Integer limit){
        List<Events> events = eventService.getAllEvents(page , limit);
        ApiResponse<List<Events>> response = ApiResponse.<List<Events>>builder()
                .message("All Events have been successfully fetched.")
                .status(HttpStatus.OK)
                .payload(events)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end get all events

    // get event by id
    @GetMapping("/{id}")
    @Operation(summary = "Get Event by ID")
    public ResponseEntity<ApiResponse<Events>> getEventById(@PathVariable Integer id){
        Events event = eventService.getEventById(id);
        ApiResponse<Events> response  = ApiResponse.<Events>builder()
                .message("The Event has been successfully founded.")
                .status(HttpStatus.OK)
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of get event by id

    // create event
    @PostMapping
    @Operation(summary = "Create Event")
    public ResponseEntity<ApiResponse<Events>> createEvent(@RequestBody @Valid EventRequest eventRequest){
        Events event = eventService.createEvent(eventRequest);
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message("he Event has been successfully created.")
                .status(HttpStatus.CREATED)
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    // end of create event

    // update event
    @PutMapping("/{id}")
    @Operation(summary = "Update Event")
    public ResponseEntity<ApiResponse<Events>> updateEventById( @PathVariable Integer id, @RequestBody EventRequest eventRequest){
        Events events = eventService.updateEventById(id,eventRequest);
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message("he Event has been successfully updated.")
                .status(HttpStatus.OK)
                .payload(events)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);

    }

    // delete events
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Event")
    public ResponseEntity<ApiResponse<Events>> deleteEventById(@PathVariable Integer id){
        eventService.deleteEventById(id);
        ApiResponse<Events> response = ApiResponse.<Events>builder()
                .message("he Event has been successfully deleted.")
                .status(HttpStatus.OK)
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
    // end of delete events



}
