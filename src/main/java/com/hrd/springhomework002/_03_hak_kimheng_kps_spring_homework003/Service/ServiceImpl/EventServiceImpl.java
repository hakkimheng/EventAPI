package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.ServiceImpl;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Attendee;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.EventRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Events;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Venue;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository.AttendeeRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository.EventRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository.VenueRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.EventService;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.exception.NotFoundException;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.exception.WrongInputException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    // inject eventRepository
    private final EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // get all event service
    @Override
    public List<Events> getAllEvents(Integer page, Integer limit) {
        // check validation not allow page and limit <= 0
        if(page<=0 || limit<=0){
            throw new WrongInputException("Page and Limit must be greater than 0!");
        }
       return eventRepository.getAllEvents(page,limit);
    }

    // create event service
    @Override
    public Events createEvent(EventRequest eventRequest) {
        // get attendee and venues for check have or not
        List<Attendee> attendee = eventRepository.getAllAttendees();
        List<Venue> venues = eventRepository.getAllVenue();

        if(attendee !=null) {
            // check validation for not allow venue id <= 0
            if (eventRequest.getVenueId()==0){
                throw new WrongInputException("Venue Id Must be greater than 0!");
            }
            // use stream for find have venue or don't have
            if(venues.stream().noneMatch(v -> v.getVenueId().equals(eventRequest.getVenueId()))){
                throw new NotFoundException("Venue ID " + eventRequest.getVenueId() + " Not Found");
            }
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                // check validation for not allow attendee id <= 0
                if(attendeeId == 0){
                    throw new WrongInputException("Attendee Id Must be greater than 0!");
                }
                // use stream for find have attendee or don't have
                if (attendee.stream().noneMatch(att -> att.getAttendeeId().equals(attendeeId))) {
                    throw new NotFoundException("Attendee ID " + attendeeId + " Not Found");
                }

            }
            // insert all field without event attendee
            Events events = eventRepository.insertEvent(eventRequest);
            // loop for get attendee id list
            for (Integer attId : eventRequest.getAttendeeId()){
                // insert event attendee
                eventRepository.createEventAttendee(events.getEventId(), attId);
            }
            return eventRepository.getEventById(events.getEventId());
        }else {
            throw new NotFoundException("Attendee ID Can't Found");
        }
    }

    // find event by id service
    @Override
    public Events getEventById(Integer id) {
        if(id <= 0){
            throw new WrongInputException("ID must be greater than 0 !");
        }
        Events events = eventRepository.getEventById(id);
        if(events == null){
            throw new NotFoundException("Event ID " + id + " Not Found!");
        }
        return events;
    }

    // delete event service
    @Override
    public void deleteEventById(Integer id) {
        if(id<=0){
            throw new WrongInputException("Id must be greater than 0!");
        }
        Events events = eventRepository.deleteEventById(id);
        if(events == null){
            throw new NotFoundException("Event ID " + id + " Not Found!");
        }
    }

    // update event service
    @Override
    public Events updateEventById(Integer id, EventRequest eventRequest) {
        List<Venue> venuesList = eventRepository.getAllVenue();
        List<Attendee> attendeesList = eventRepository.getAllAttendees();
        // check not allow id <= 0
        if(id <= 0 ){
            throw new WrongInputException("Id must be greater than 0!");
        }
        if(eventRequest.getVenueId() <= 0){
            throw new WrongInputException("Venue Id must be greater than 0!");
        }
        if(venuesList.stream().noneMatch(v-> v.getVenueId().equals(eventRequest.getVenueId()))){
            throw new NotFoundException("Venue ID " + eventRequest.getVenueId() + " Not Found!");
        }
        // check have

        Events events = eventRepository.updateEventById(id,eventRequest);
        if (events == null){
            throw new NotFoundException("Event ID " + id + " Not Found!");
        }
        eventRepository.deleteEventAttendeeByEventId(id);
        for(Integer attendeeId : eventRequest.getAttendeeId()) {
            if(attendeeId <=0){
                throw new WrongInputException("Attendee Id must be greater than 0!");
            }
            if(attendeesList.stream().noneMatch(att-> att.getAttendeeId().equals(attendeeId))){
                throw new NotFoundException("Attendee ID " + attendeeId + " Not Found!");
            }
            eventRepository.createEventAttendee(id, attendeeId);
        }

        return eventRepository.getEventById(id);
    }
}
