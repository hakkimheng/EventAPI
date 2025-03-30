package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.VenueRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    // get all venue
    @Select("""
    SELECT * FROM venue
    offset #{limit} * (#{page} -1)
    limit #{limit};
""")
    @Results(id = "venueMapping" ,value = {
        @Result(property = "venueId", column = "venue_id"),
        @Result(property = "venueName", column = "venue_name"),
        @Result(property = "venueLocation", column = "location")
    })
    List<Venue> getAllVenue(Integer page , Integer limit);

    // insert venus
    @Select("""
    insert into venue(venue_name, location) values(#{venue.venueName}, #{venue.venueLocation})
    RETURNING *
""")
    @ResultMap("venueMapping")
    Venue insertVenue(@Param("venue") VenueRequest venueRequest);

    //get venue by id
    @Select("""
    select * from venue
    where venue_id= #{id}
    """)
    @ResultMap("venueMapping")
    Venue getVenueById(Integer id);

    @Select("""
    update venue set venue_name = #{venue.venueName},
                     location = #{venue.venueLocation}
    where venue_id = #{venueId}
    Returning *;
""")
    @ResultMap("venueMapping")
    Venue updateVenue(Integer venueId, @Param("venue") VenueRequest venueRequest);

    @Select("""
            DELETE from venue
            where venue_id = #{id}
            returning *;
            """)
    @ResultMap("venueMapping")
    Venue deleteVenue(Integer id);
}
