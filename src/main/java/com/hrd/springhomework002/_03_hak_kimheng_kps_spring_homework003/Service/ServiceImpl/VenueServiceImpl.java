package com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.ServiceImpl;

import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.DTO.Request.VenueRequest;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Model.Venue;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Repository.VenueRepository;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.Service.VenueService;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.exception.NotFoundException;
import com.hrd.springhomework002._03_hak_kimheng_kps_spring_homework003.exception.WrongInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VenueServiceImpl implements VenueService {

    // inject VenueRepository
    private final VenueRepository venueRepository;
    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    // get all venue
    @Override
    public List<Venue> getAllVenue(Integer page, Integer limit) {
        // check validation not allow page and limit <= 0
        if(page <=0 || limit<=0){
            throw new WrongInputException("Page or Limit must be greater than 0");
        }
        return venueRepository.getAllVenue(page, limit);
    }

    // create venue
    @Override
    public Venue createVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }

    // get venue by id
    @Override
    public Venue getVenueById(Integer id) {
        // check validation not allow id <=0
        if(id <= 0){
            throw new WrongInputException("id must be greater than 0");
        }
        Venue getVenue = venueRepository.getVenueById(id);
        // check if venue return null not found
        if(getVenue == null){
            throw new NotFoundException("Venue Id : " + id + " Not Found");
        }
        return getVenue;
    }

    @Override
    public Venue updateVenue(Integer venueId , VenueRequest venueRequest) {
        Venue venue = venueRepository.updateVenue(venueId, venueRequest);
        // check validation not allow id <=0
        if(venueId <= 0){
            throw new WrongInputException("Id must be greater than 0!");
        }
        // check if venue return null not found
        if(venue == null){
            throw new NotFoundException("Venue Id " + venueId + " Not Found");
        }
        return venue;
    }

    @Override
    public void deleteVenue(Integer id) {
        // check validation not allow id <=0
        if(id<= 0){
            throw new WrongInputException("Id must be greater than 0!");
        }
        Venue venue = venueRepository.deleteVenue(id);
        // check if venue return null not found
        if(venue == null){
            throw new NotFoundException("Venue Id " + id + " Not Found");
        }
    }
}
