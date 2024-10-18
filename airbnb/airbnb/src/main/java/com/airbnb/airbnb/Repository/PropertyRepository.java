package com.airbnb.airbnb.Repository;

import com.airbnb.airbnb.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("select p from Property p JOIN p.location l where l.locationName = :location name")
 List<Property> findPropertyByLocation(@Param("locationName") String locationName);


//@Query("select p from Property p where p.location.locationName = :locationName or c.countryName")
//List<Property> findPropertiesByLocationName(@Param("locationName") String locationName);


}