package com.xcoder.smart.parking.system.entry;

import com.xcoder.smart.parking.system.event.VehicleExitedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExitService {
    private final ParkingEntryRepository parkingEntryRepository;
    private final ApplicationEventPublisher publisher;

    public void vehicleExit(String vehicleNumber) {
        ParkingEntry parkingEntry = parkingEntryRepository.findByVehicleNumberAndActiveIsTrue(vehicleNumber)
                .orElseThrow(() -> new RuntimeException("No active entry found for vehicle " + vehicleNumber));
        parkingEntry.setExitTime(LocalDateTime.now());
        parkingEntry.setActive(false);
        parkingEntryRepository.save(parkingEntry);

        publisher.publishEvent(new VehicleExitedEvent(vehicleNumber, parkingEntry.getEntryTime(), parkingEntry.getExitTime()));
    }
}
