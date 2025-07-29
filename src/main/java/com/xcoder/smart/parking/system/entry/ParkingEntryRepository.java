package com.xcoder.smart.parking.system.entry;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingEntryRepository extends JpaRepository<ParkingEntry, Long> {
    Optional<ParkingEntry> findByVehicleNumberAndActiveIsTrue(String vehicleNumber);
}
