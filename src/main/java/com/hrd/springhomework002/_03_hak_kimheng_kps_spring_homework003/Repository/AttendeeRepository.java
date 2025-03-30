package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Attendee;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
       SELECT * FROM attendee
       offset #{limit} * (#{page} -1)
       limit #{limit}
       """
           )
    @Results(id = "attendeeMapping",value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "attendeeEmail", column = "email")
    })
    List<Attendee> getAllAttendees(Integer page, Integer limit);

    @Select("""
    INSERT INTO attendee(attendee_name, email) VALUES (#{attendee.attendeeName},#{attendee.attendeeEmail})
    RETURNING *;
""")
    @ResultMap("attendeeMapping")
    Attendee insertAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    @Select("""
    SELECT * FROM attendee
    WHERE attendee_id = #{id}
""")
    @ResultMap("attendeeMapping")
    Attendee getAttendeeById(Integer id);

    @Select("""
    UPDATE attendee set attendee_name = #{attendee.attendeeName},
                        email = #{attendee.attendeeEmail}
    WHERE attendee_id = #{id}
    RETURNING *;
    """)
    @ResultMap("attendeeMapping")
    Attendee updateAttendee(@Param("attendee") AttendeeRequest attendeeRequest, Integer id);

    @Select("""
    DELETE FROM attendee
    WHERE attendee_id = #{id}
    RETURNING *;
""")
    @ResultMap("attendeeMapping")
    Attendee deleteAttendee(Integer id);
}

