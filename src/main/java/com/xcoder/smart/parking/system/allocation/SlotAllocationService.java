package com.xcoder.smart.parking.system.allocation;

import com.xcoder.smart.parking.system.event.VehicleEnteredEvent;
import com.xcoder.smart.parking.system.event.VehicleExitedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlotAllocationService {
    private final SlotRepository slotRepository;

    @EventListener
    public void handleVehicleEntry(VehicleEnteredEvent event) {
        Slot slot = slotRepository.findFirstByAvailableIsTrue()
                .orElseThrow(() -> new RuntimeException("No available slot found"));
        slot.setAvailable(false);
        slot.setVehicleNumber(event.vehicleNumber());

        slotRepository.save(slot);
        log.info("Allocated Slot {} to vehicle {}", slot.getSlotCode(), event.vehicleNumber());
    }

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent event) {
        slotRepository.findByVehicleNumber(event.vehicleNumber())
                .ifPresentOrElse(slot -> {
                    slot.setAvailable(true);
                    slot.setVehicleNumber(null);
                    slotRepository.save(slot);
                    log.info("Slot {} released for vehicle {}", slot.getSlotCode(), event.vehicleNumber());
                }, () -> {
                    throw new RuntimeException("No slot found for vehicle " + event.vehicleNumber());
                });
    }
}
