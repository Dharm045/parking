package com.parking.parking.repository;

import com.parking.parking.model.LotEntity;
import com.parking.parking.model.ParkEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LotRepository {

    Map<Integer, LotEntity> lotData = new HashMap<>();

    public Integer add(LotEntity lotEntity) {
        int id = lotData.size() + 1;
        lotEntity.setAvailableCapacities(lotEntity.getCapacities());
        lotData.put(id, lotEntity);
        return id;
    }

    public LotEntity byId(Integer id) {
        return lotData.get(id);
    }
}
