package com.parking.parking.controller;

import com.parking.parking.model.ParkEntity;
import com.parking.parking.request.ParkRequest;
import com.parking.parking.response.ParkResponse;
import com.parking.parking.service.ParkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
@Slf4j
public class ParkingController {

    @Autowired
    private ParkService parkService;

    @PostMapping("park")
    public ResponseEntity<String> park(@RequestBody ParkRequest parkRequest) {
        try {
            parkService.park(parkRequest);
        } catch (Exception e) {
            log.error("park:: error ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success.", HttpStatus.OK);
    }

    @PostMapping("exit")
    public ResponseEntity<String> exit(@RequestBody ParkRequest parkRequest) {
        Integer amount = null;
        try {
            amount = parkService.exit(parkRequest);
        } catch (Exception e) {
            log.error("Exit:: Error ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("success. amount: "+amount, HttpStatus.OK);
    }

    @GetMapping("history")
    public ResponseEntity<ParkResponse> history(@RequestParam("number") String number) {
        ParkResponse parkResponse = new ParkResponse();
        parkResponse.setStatus("success");
        parkResponse.setData(parkService.loadHistory(number));
        return new ResponseEntity<>(parkResponse, HttpStatus.OK);
    }


}
