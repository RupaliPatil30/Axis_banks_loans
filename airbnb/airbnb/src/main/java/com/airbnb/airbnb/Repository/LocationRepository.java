package com.airbnb.airbnb.Repository;

import com.airbnb.airbnb.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}