package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Attendee;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.AttendeeRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendeeService{

    List<Attendee> getAllAttendees(Integer page, Integer limit);

    Attendee createAttendee(AttendeeRequest attendeeRequest);

    Attendee getAttendeeById(Integer id);

    Attendee updateAttendee(@Valid AttendeeRequest attendeeRequest, Integer id);

    void deleteAttendee(Integer id);
}
