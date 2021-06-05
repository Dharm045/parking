package com.parking.parking.repository;

import com.parking.parking.Enum.PARKSTATUS;
import com.parking.parking.model.ParkEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkRepository {

    List<ParkEntity> parkedList = new ArrayList<>();

    public int park(ParkEntity parkEntity) {
        int id = parkedList.size() + 1;
        parkEntity.setId(id);
        parkEntity.setStatus(PARKSTATUS.PARKED);
        parkedList.add(parkEntity);
        return id;
    }

    public void markExit(ParkEntity parkEntity) {
        parkEntity.setStatus(PARKSTATUS.EXITED);
    }

    public ParkEntity findByNumber(String number, PARKSTATUS status) {
        for(ParkEntity parkEntity : parkedList) {
            if(parkEntity.getNumber().equals(number) && parkEntity.getStatus() == status) return parkEntity;
        }
        return null;
    }

    public List<ParkEntity> loadHistory(String number) {
        List<ParkEntity> history = new ArrayList<>();

        for(ParkEntity parkEntity : parkedList) {
            if(parkEntity.getNumber().equals(number)) {
                history.add(parkEntity);
            }
        }
        return history;
    }

}
