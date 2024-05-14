package com.vietqrservice.service;

import com.vietqrservice.constant.Type;
import com.vietqrservice.dto.AccountDTO;

public interface QRGenerateService {
    public String getVietQRCode(Type type, AccountDTO accountDTO);
    public void generateImageVietQR(Type type, AccountDTO accountDTO);
}
