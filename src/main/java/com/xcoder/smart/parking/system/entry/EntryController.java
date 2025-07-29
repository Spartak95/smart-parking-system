package com.xcoder.smart.parking.system.entry;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class EntryController {
    private final EntryService entryService;
    private final ExitService exitService;

    @PostMapping("/entry")
    public ResponseEntity<String> vehicleEntry(@RequestParam String vehicleNumber) {
        entryService.vehicleEntry(vehicleNumber);
        return ResponseEntity.ok("Vehicle " + vehicleNumber + " entered successfully");
    }

    @PostMapping("/exit")
    public ResponseEntity<String> vehicleExit(@RequestParam String vehicleNumber) {
        exitService.vehicleExit(vehicleNumber);
        return ResponseEntity.ok("Vehicle " + vehicleNumber + " exited successfully");
    }
}
