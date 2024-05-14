package com.vietqrservice.utils;

import java.util.zip.CRC32;

public class Helper {

    // Hàm chuyển đổi chuỗi thành CRC 4 ký tự
    public static String convertToCRC32(String vietQRString) {
        // Khởi tạo đối tượng CRC32
        CRC32 crc32 = new CRC32();
        // Cập nhật giá trị CRC32 từ chuỗi đầu vào
        crc32.update(vietQRString.getBytes());
        // Lấy giá trị CRC32
        long crcValue = crc32.getValue();
        // Chuyển giá trị CRC32 thành chuỗi hexadecimal
        String crcHex = Long.toHexString(crcValue);
        // Cắt chỉ lấy 4 ký tự cuối cùng
        String crc = crcHex.substring(Math.max(0, crcHex.length() - 4));
        // Trả về CRC 4 ký tự
        return crc;
    }

    // Hàm chuyển đổi chuỗi thành CRC 4 ký tự sử dụng CRC-16-CCITT
    public static String convertToCRC16(String vietQRString) {
        int polynomial = 0x1021; // CRC-16-CCITT Giao thức
        int crc = 0xFFFF; // Giá trị khởi tạo cho CRC-16-CCITT

        byte[] bytes = vietQRString.getBytes();
        for (byte b : bytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }

        crc &= 0xFFFF; // Chỉ lấy 16-bit kết quả
        // Chuyển giá trị CRC thành chuỗi hexadecimal
        String crcHex = Integer.toHexString(crc).toUpperCase();

        // Đảm bảo chuỗi có đủ 4 ký tự
        while (crcHex.length() < 4) {
            crcHex = "0" + crcHex;
        }

        return crcHex;
    }
}
