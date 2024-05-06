package com.vietqrservice.service;

import com.vietqrservice.entity.VietQR;

import java.util.Map;

public interface QRScanService {
    public VietQR dataVietQR(String QRString);
    public void checkKey(String id, int length, String content, String QRString, VietQR vietQR);
    public void handleCase38(String content,  VietQR vietQR);
}
