package com.parking.parking.service.impl;

import com.parking.parking.Enum.PARKSTATUS;
import com.parking.parking.model.LotEntity;
import com.parking.parking.model.ParkEntity;
import com.parking.parking.repository.LotRepository;
import com.parking.parking.repository.ParkRepository;
import com.parking.parking.request.ParkRequest;
import com.parking.parking.service.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ParkServiceImpl implements ParkService {

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private LotRepository lotRepository;

    @Override
    public void park(ParkRequest parkRequest) throws Exception {

        LotEntity lotEntity = lotRepository.byId(parkRequest.getLotId());
        if(lotEntity == null) {
            throw new Exception("Invalid lot.");
        }

        int cap = lotEntity.getAvailableCapacities().get(parkRequest.getVehicalType());
        if(cap == 0) {
            throw new Exception("lot is full.");
        }

        ParkEntity parkEntity = new ParkEntity();

        BeanUtils.copyProperties(parkRequest, parkEntity);
        parkEntity.setParkAt(System.currentTimeMillis());
        parkEntity.setStatus(PARKSTATUS.PARKED);
        parkRepository.park(parkEntity);
        lotEntity.getAvailableCapacities().put(parkRequest.getVehicalType(), cap-1);
    }

    @Override
    public Integer exit(ParkRequest parkRequest) throws Exception {

        ParkEntity parkEntity = parkRepository.findByNumber(parkRequest.getNumber(), PARKSTATUS.PARKED);

        if(parkEntity == null) {
            throw new Exception("No vehicle found.");
        }

        LotEntity lotEntity = lotRepository.byId(parkEntity.getLotId());

        long parkAt = parkEntity.getParkAt();
        long exitAt = System.currentTimeMillis();

        Double timeHr = Math.ceil((exitAt - parkAt) / (1000));
        Integer actualTime = timeHr.intValue();

        Map<Integer, Integer> rateCard = lotEntity.getRateCards().get(parkEntity.getVehicalType());

        Integer amount = calculateCharge(rateCard, actualTime);

        log.info("exit:: amount {} for parkedTime {}", amount, actualTime);

        parkEntity.setAmount(amount);
        parkEntity.setExitAt(exitAt);
        parkEntity.setDuration(exitAt-parkAt);
        parkRepository.markExit(parkEntity);

        int available = lotEntity.getAvailableCapacities().get(parkRequest.getVehicalType());
        lotEntity.getAvailableCapacities().put(parkRequest.getVehicalType(), available+1);
        return amount;
    }

    private Integer calculateCharge(Map<Integer, Integer> rateCard, Integer parkedTime) {

        int amount = 0;
        for(Integer hr : rateCard.keySet()) {
            Integer rate = rateCard.get(hr);
            amount = amount + rate;
            if(hr >= parkedTime) {
                break;
            }
        }
        return amount;
    }

    public List<ParkEntity> loadHistory(String number) {
        List<ParkEntity> parkEntities = parkRepository.loadHistory(number);
        return parkEntities;
    }

}
