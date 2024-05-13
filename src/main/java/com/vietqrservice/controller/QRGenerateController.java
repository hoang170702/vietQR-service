package com.vietqrservice.controller;

import com.vietqrservice.constant.Type;
import com.vietqrservice.dto.AccountDTO;
import com.vietqrservice.service.QRGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gen-viet-qr")
public class QRGenerateController {
    @Autowired
    private QRGenerateService qrGenerateService;

    @PostMapping("get-qr")
    public ResponseEntity<String> getQR(@RequestBody AccountDTO accountDTO) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(this.qrGenerateService.getVietQRCode(Type.STATIC, accountDTO));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
