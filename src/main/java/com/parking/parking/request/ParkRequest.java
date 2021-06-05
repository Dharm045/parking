package com.parking.parking.request;

import com.parking.parking.Enum.VEHICLETYPE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkRequest {

    private String number;
    private Integer lotId;
    private VEHICLETYPE vehicalType;
}
