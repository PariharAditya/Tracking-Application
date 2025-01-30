package com.trackingapplication.controller;

import com.trackingapplication.dto.DeviceDTO;
import com.trackingapplication.dto.ErrorResponse;
import com.trackingapplication.exception.CustomException;
import com.trackingapplication.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/getAll")
    public ResponseEntity<List<DeviceDTO>> getAllDevices(){

        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    //make it optional as ID exist or !
    @GetMapping("/{id}")
    public ResponseEntity<DeviceDTO> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping("/create")
//    public ResponseEntity<Device> createDevice(@RequestBody Device device){
//            return ResponseEntity.ok(deviceService.createDevice(device));
//    }
//
    @PostMapping("/create")
    public ResponseEntity<?> createDevice(@RequestBody DeviceDTO deviceDTO) {
        try {
            DeviceDTO device = deviceService.createDevice(deviceDTO);

            return ResponseEntity.ok(device);
        } catch (CustomException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable Long id) {
        if (deviceService.getDeviceById(id).isPresent()) {
            deviceService.deleteDeviceById(id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build(); // no chaining deleteDeviceById as return is void
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable Long id, @RequestBody DeviceDTO updatedDevice){
        return deviceService.getDeviceById(id)
                .map(device -> {
                    device.setDeviceType(updatedDevice.getDeviceType());
                    return ResponseEntity.ok(deviceService.updateDevice(id, device));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
