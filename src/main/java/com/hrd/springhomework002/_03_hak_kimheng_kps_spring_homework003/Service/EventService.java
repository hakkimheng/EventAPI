package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.EventRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Events;
import jakarta.validation.Valid;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents(Integer page, Integer limit);

    Events createEvent(@Valid EventRequest eventRequest);

    Events getEventById(Integer id);

    void deleteEventById(Integer id);

    Events updateEventById(Integer id, EventRequest eventRequest);
}
