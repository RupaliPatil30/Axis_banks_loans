package com.airbnb.airbnb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "location_name", nullable = false, unique = true)
    private String locationName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
