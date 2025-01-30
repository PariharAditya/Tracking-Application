package com.trackingapplication.dto;

import com.trackingapplication.entity.DeviceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDTO {

    private Long id;
    private String macAddress;
    private String serialNo;
    private DeviceType deviceType;

    private String position;
    private String latitude;
    private String longitude;
    private String status;
    private String IMEI_number;

}