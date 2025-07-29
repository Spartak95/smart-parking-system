package com.xcoder.smart.parking.system.notification;

import com.xcoder.smart.parking.system.event.VehicleEnteredEvent;
import com.xcoder.smart.parking.system.event.VehicleExitedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    @EventListener
    public void notifyOnVehicleEntry(VehicleEnteredEvent event) {
        log.info("Notification: Vehicle {} entered at {}. Welcome!", event.vehicleNumber(), event.entryTime());
    }

    @EventListener
    public void notifyOnVehicleExit(VehicleExitedEvent event) {
        log.info("Notification: Vehicle {} exited at {}. Thank you for parking!", event.vehicleNumber(), event.exitTime());
    }
}
