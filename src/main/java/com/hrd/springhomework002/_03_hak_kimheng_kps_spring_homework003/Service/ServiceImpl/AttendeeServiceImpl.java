package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.ServiceImpl;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Attendee;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.AttendeeRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository.AttendeeRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.AttendeeService;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.exception.NotFoundException;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.exception.WrongInputException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    // inject AttendeeRepository
    private final AttendeeRepository attendeeRepository;
    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    // get all attendees
    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer limit) {
        return attendeeRepository.getAllAttendees(page,limit);
    }

    // create attendee
    @Override
    public Attendee createAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    //get attendee by id
    @Override
    public Attendee getAttendeeById(Integer id) {
        if(id <= 0){
            throw new WrongInputException("Attendee ID must be greater than 0!");
        }
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null){
            throw new NotFoundException("Attendee ID " + id + " Not Found");
        }
        return attendee;
    }

    // update attendee
    @Override
    public Attendee updateAttendee(AttendeeRequest attendeeRequest, Integer id) {
        if(id<= 0){
            throw new WrongInputException("Attendee ID must be greater than 0!");
        }
        Attendee attendee = attendeeRepository.updateAttendee(attendeeRequest,id);
        if(attendee == null){
            throw new NotFoundException("Attendee ID " + id + " Not Found");
        }
        return attendee;
    }

    // delete attendee
    @Override
    public void deleteAttendee(Integer id) {
        if(id<=0){
            throw new WrongInputException("Attendee ID must be greater than 0!");
        }
        Attendee attendee = attendeeRepository.deleteAttendee(id);
        if (attendee == null){
            throw new NotFoundException("Attendee ID " + id + " Not Found");
        }
    }
}
