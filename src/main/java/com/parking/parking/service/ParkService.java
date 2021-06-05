package com.parking.parking.service;

import com.parking.parking.model.ParkEntity;
import com.parking.parking.request.ParkRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkService {

    public void park(ParkRequest parkRequest) throws Exception;
    public Integer exit(ParkRequest parkRequest) throws Exception;
    public List<ParkEntity> loadHistory(String number);

}
