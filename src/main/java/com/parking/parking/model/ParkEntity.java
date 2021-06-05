package com.parking.parking.model;

import com.parking.parking.Enum.PARKSTATUS;
import com.parking.parking.Enum.VEHICLETYPE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkEntity {

    private int id;
    private String number;
    private Integer lotId;
    private VEHICLETYPE vehicalType;

    private Long parkAt;
    private Long exitAt;
    private PARKSTATUS status;
    private Integer amount;
    private Long duration;

}
