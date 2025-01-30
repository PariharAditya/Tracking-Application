package com.trackingapplication.service;

import com.trackingapplication.dto.DeviceDTO;

import java.util.List;
import java.util.Optional;

public interface DeviceService {
    List<DeviceDTO> getAllDevices();
    Optional<DeviceDTO> getDeviceById(Long id);
    DeviceDTO createDevice(DeviceDTO device);
    void deleteDeviceById(Long id);
    DeviceDTO updateDevice(Long id, DeviceDTO updatedDevice);
}
