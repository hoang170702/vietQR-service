package com.vietqrservice.utils;

import java.util.zip.CRC32;

public class Helper {

    // Hàm chuyển đổi chuỗi thành CRC 4 ký tự
    public static String convertToCRC(String vietQRString) {
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
}
