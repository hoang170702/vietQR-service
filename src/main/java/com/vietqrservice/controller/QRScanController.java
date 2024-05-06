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

    @GetMapping("/json")
    public ResponseEntity<VietQR> dataJsonQR() {
        String QRString = "0002010102111531397007040052044600006789177200238550010A000000727012500069704230111678917720020208QRIBFTTA5204513753037045802VN5405200005914NGUYEN HUU HOA6006Ha Noi630469A3";
        return  ResponseEntity.status(HttpStatus.OK).body(this.qrScanService.dataVietQR(QRString));
    }

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
