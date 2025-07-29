package com.xcoder.smart.parking.system.billing;

import com.xcoder.smart.parking.system.event.VehicleExitedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillingService {
    private final BillingRecordRepository billingRecordRepository;

    @EventListener
    public void handleVehicleExit(VehicleExitedEvent event) {
        Duration duration = Duration.between(event.entryTime(), event.exitTime());
        double amount = Math.max(20, (duration.toMinutes() / 60.0) * 50);
        BillingRecord billingRecord = new BillingRecord(null, event.vehicleNumber(), amount, event.exitTime());
        billingRecordRepository.save(billingRecord);
        log.info("Billing amount {} for vehicle {}", amount, event.vehicleNumber());
    }
}
