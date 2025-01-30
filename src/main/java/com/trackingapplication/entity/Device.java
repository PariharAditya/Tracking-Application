package com.trackingapplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Data
@Table(name = "devices", uniqueConstraints = @UniqueConstraint(columnNames = {"macAddress", "serialNo"}))

public class Device extends BaseEntity<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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