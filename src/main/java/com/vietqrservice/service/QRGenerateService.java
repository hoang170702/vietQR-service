package com.vietqrservice.service;

import com.vietqrservice.constant.Type;
import com.vietqrservice.dto.AccountDTO;

public interface QRGenerateService {
    public int crc16(final byte[] buffer);
    public String getCrc16Valid(String vietQRCode);
    public String getVietQRCode(Type type, AccountDTO accountDTO);
}
