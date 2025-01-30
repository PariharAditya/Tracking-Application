package com.trackingapplication.repository;

import com.trackingapplication.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Long> {

    boolean existsByMacAddressAndSerialNo(String macAddress, String serialNo);
}
