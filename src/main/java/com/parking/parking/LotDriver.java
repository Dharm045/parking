package com.parking.parking;

import static com.parking.parking.Enum.VEHICLETYPE.BIKE;
import static com.parking.parking.Enum.VEHICLETYPE.HATCHBACK;
import static com.parking.parking.Enum.VEHICLETYPE.SUV;

import com.parking.parking.Enum.VEHICLETYPE;
import com.parking.parking.model.LotEntity;
import com.parking.parking.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
@Slf4j
public class LotDriver {

    @Autowired
    private LotRepository lotRepository;

    @PostConstruct
    public void load() {

        List<VEHICLETYPE> types = new ArrayList<>();
        types.add(BIKE);
        types.add(HATCHBACK);
        types.add(SUV);

        Map<VEHICLETYPE, Integer> capacities = new HashMap<>();
        capacities.put(BIKE, 2);
        capacities.put(HATCHBACK, 10);
        capacities.put(SUV, 5);

        Map<VEHICLETYPE, Map<Integer, Integer>> rateCards = new HashMap<>();

        TreeMap<Integer, Integer> bikeRate = new TreeMap<>();
        Map<Integer, Integer>  hbRate = new HashMap<>();
        Map<Integer, Integer> subRate = new HashMap<>();

        bikeRate.put(60, 20);
        bikeRate.put(300, 50);
        bikeRate.put(600, 100);

        rateCards.put(BIKE, bikeRate);
        rateCards.put(HATCHBACK, hbRate);
        rateCards.put(SUV, subRate);

        LotEntity lotEntity1 = new LotEntity();
        lotEntity1.setName("1");
        lotEntity1.setVehicletypes(types);
        lotEntity1.setCapacities(capacities);
        lotEntity1.setRateCards(rateCards);

        lotRepository.add(lotEntity1);

        log.info(" ====== lot data loaded ========= ");

    }

}
