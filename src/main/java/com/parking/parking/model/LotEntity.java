package com.parking.parking.model;

import com.parking.parking.Enum.VEHICLETYPE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class LotEntity {

    private Integer id;
    private String name;
    private List<VEHICLETYPE> vehicletypes;
    private Map<VEHICLETYPE, Integer> capacities;
    private Map<VEHICLETYPE, Map<Integer, Integer>> rateCards;
    private Map<VEHICLETYPE, Integer> availableCapacities;

}
