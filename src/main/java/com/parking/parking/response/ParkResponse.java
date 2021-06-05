package com.parking.parking.response;

import com.parking.parking.model.ParkEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkResponse {
    private String status;
    private List<ParkEntity> data;
}
