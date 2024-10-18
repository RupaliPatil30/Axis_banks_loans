package com.airbnb.airbnb.controller;

import com.airbnb.airbnb.Repository.PropertyRepository;
import com.airbnb.airbnb.entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }




        //return;
           @GetMapping("/{properties}")
           public ResponseEntity<List<Property>>findProperty(@PathVariable String locationName){
        List<Property>properties=propertyRepository.findPropertyByLocation(locationName);


               return new ResponseEntity<>(properties, HttpStatus.OK);
           }
}


