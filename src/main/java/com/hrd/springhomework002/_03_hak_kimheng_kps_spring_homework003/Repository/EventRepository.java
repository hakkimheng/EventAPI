package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Attendee;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.AttendeeRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.EventRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Events;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Venue;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EventRepository {
    @Select("""
        SELECT * FROM events
        offset #{limit} * (#{page} -1)
        limit #{limit};
        """)
    @Results(id = "eventsMapping", value = {
            @Result(property = "eventId",column = "events_id"),
            @Result(property = "eventName", column = "events_name"),
            @Result(property = "eventDate", column = "events_date"),
            @Result(property = "venue", column = "venue_id",
                one = @One(select = "com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository.VenueRepository.getVenueById")
            ),
            @Result(property = "attendeeList" , column = "events_id",
            many = @Many(select = "findAttendeeByEventId")
            )
    })
    List<Events> getAllEvents(Integer page , Integer limit);



    @Select("""
    INSERT INTO events(events_name, events_date, venue_id) VALUES (#{event.eventName},#{event.eventDate},#{event.venueId})
    RETURNING *;
    """)
   @ResultMap("eventsMapping")
    Events insertEvent(@Param("event") EventRequest eventRequest);

    // find attendee by event id
    @Select("""
    SELECT * FROM attendee
    INNER JOIN events_attendee e ON attendee.attendee_id = e.attendee_id
    WHERE e.events_id = #{eventId}
    """)
    @Results(id = "attendeeMapping",value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "attendeeEmail", column = "email")
    })
    List<Attendee> findAttendeeByEventId(Integer eventId);


    @Select("""
    INSERT INTO events_attendee(events_id, attendee_id) VALUES (#{eventId}, #{attendeeId})
""")
   void createEventAttendee(Integer eventId, Integer attendeeId);

    // get all attendee
    @Select("SELECT * FROM attendee")
    @ResultMap("attendeeMapping")
    List<Attendee> getAllAttendees();

    // get all venue
    @Select("""
    SELECT * FROM venue
""")
    @Results(id = "venueMapping" ,value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "venueLocation", column = "location")
    })
    List<Venue> getAllVenue();

    @Select("""
    SELECT * FROM events WHERE events_id = #{id}
""")
    @ResultMap("eventsMapping")
    Events getEventById(Integer id);

    @Select("""
    DELETE FROM events WHERE events_id = #{id}
    RETURNING *;
""")
    @ResultMap("eventsMapping")
    Events deleteEventById(Integer id);

    @Select("""
    UPDATE events SET events_name = #{event.eventName},
                      events_date = #{event.eventDate},
                      venue_id = #{event.venueId}
    WHERE events_id = #{id}
    RETURNING *;
    """)
    @ResultMap("eventsMapping")
    Events updateEventById(Integer id,@Param("event") EventRequest eventRequest);

    @Delete("""
    DELETE FROM events_attendee WHERE events_id = #{id}
    """)
    void deleteEventAttendeeByEventId(Integer id);

}
