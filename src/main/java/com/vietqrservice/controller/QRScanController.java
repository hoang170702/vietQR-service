package com.vietqrservice.controller;

import com.vietqrservice.entity.VietQR;
import com.vietqrservice.service.QRScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viet-qr")
public class QRScanController {
    @Autowired
    private QRScanService qrScanService;

    @PostMapping("/save-qrdata")
    public ResponseEntity<String> saveQRData(@RequestBody String QRString) {
        this.qrScanService.save(this.qrScanService.dataVietQR(QRString));
        return  ResponseEntity.status(HttpStatus.OK).body("save data success");
    }

    @GetMapping("get-all-qr")
    public ResponseEntity<List<VietQR>> getAllQR() {
        return ResponseEntity.status(HttpStatus.OK).body(this.qrScanService.getAllVietQRs());
    }
}
