package com.airbnb.airbnb.Repository;



import com.airbnb.airbnb.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.introspector.Property;

import java.util.List;


public interface CountryRepository extends JpaRepository<Country, Long> {


  //  @Query("select c from Country c join c.locations l join country c  on p.county=c.id where l.locationName = :locationName")
    //List<Country> findCountriesByLocationName(@Param("locationName") String locationName);

    @Query("select c from Country c join c.locations l join country c  on p.county=c.id where l.locationName = :locationName")
    List<Country> findCountriesByLocationName(@Param("locationName") String locationName);
}