package com.xcoder.smart.parking.system.allocation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot, Long> {
    Optional<Slot> findFirstByAvailableIsTrue();
    Optional<Slot> findByVehicleNumber(String vehicleNumber);
}
