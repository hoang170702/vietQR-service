package com.vietqrservice.controller;

import com.vietqrservice.constant.Type;
import com.vietqrservice.dto.AccountDTO;
import com.vietqrservice.service.QRGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gen-viet-qr")
public class QRGenerateController {
    @Autowired
    private QRGenerateService qrGenerateService;

    @PostMapping("/get-qr")
    public ResponseEntity<String> getQR(@RequestBody AccountDTO accountDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.qrGenerateService.getVietQRCode(Type.STATIC, accountDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/create-qr")
    public ResponseEntity<String> createQR(@RequestBody AccountDTO accountDTO) {
        try {
            this.qrGenerateService.generateImageVietQR(Type.STATIC, accountDTO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("create qr success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
