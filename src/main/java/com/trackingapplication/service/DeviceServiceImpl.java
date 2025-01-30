package com.trackingapplication.service;

import com.trackingapplication.dto.DeviceDTO;
import com.trackingapplication.entity.Device;
import com.trackingapplication.exception.CustomException;
import com.trackingapplication.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    private final ModelMapper modelMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
    }

    // Get all devices
    public List<DeviceDTO> getAllDevices() {
        return deviceRepository.findAll().stream()
                // Convert each Device entity to DeviceDTO using ModelMapper
                .map(device -> modelMapper.map(device, DeviceDTO.class))
                .toList();
    }

    // Get a device by ID
    public Optional<DeviceDTO> getDeviceById(Long id) {
        return deviceRepository.findById(id)
                // Convert the Device entity to DeviceDTO if present
                .map(device -> modelMapper.map(device, DeviceDTO.class));
    }

    // Create a new device
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        // Validate if the device exists
        if (deviceRepository.existsByMacAddressAndSerialNo(deviceDTO.getMacAddress(), deviceDTO.getSerialNo())) {
            throw new CustomException("Device already exists");
        }

        // Convert DTO to Entity using ModelMapper
        Device deviceEntity = modelMapper.map(deviceDTO, Device.class);

        // Save the Device entity
        Device savedDevice = deviceRepository.save(deviceEntity);

        // Convert saved entity back to DTO
        return modelMapper.map(savedDevice, DeviceDTO.class);
    }

    // Delete by ID
    public void deleteDeviceById(Long id) {
        deviceRepository.deleteById(id);
        throw new CustomException("");
    }

    // Update an existing device
    public DeviceDTO updateDevice(Long id, DeviceDTO updatedDevice) {
        // Find device or throw exception
        Device existingDevice = deviceRepository.findById(id)
                .orElseThrow(() -> new CustomException("Device not found"));


//        existingDevice.setDeviceType(updatedDevice.getDeviceType());
//        existingDevice.setMacAddress(updatedDevice.getMacAddress());
//        existingDevice.setSerialNo(updatedDevice.getSerialNo());
//
        modelMapper.map(updatedDevice, existingDevice);

        // Save updated device
        Device savedDevice = deviceRepository.save(existingDevice);

        // Convert updated entity back to DTO
        return modelMapper.map(savedDevice, DeviceDTO.class);
    }

}