package com.airbnb.airbnb.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private Set<Location> locations;

    // Getters and setters

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
