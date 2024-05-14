package com.vietqrservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.vietqrservice.constant.Country;
import com.vietqrservice.constant.Type;
import com.vietqrservice.dto.AccountDTO;
import com.vietqrservice.utils.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class QRGenerateServiceImpl implements QRGenerateService {

    private static final Logger log = LoggerFactory.getLogger(QRGenerateServiceImpl.class);

    @Override
    public String getVietQRCode(Type type, AccountDTO accountDTO) {
        String caseId00 = getCaseId00();
        String caseId01 = getCaseId01(type);
        String caseId38 = getCaseId38(accountDTO.getAccountNo());
        String caseId53 = getCaseId53(accountDTO.getCountry());
        String caseId54 = getCaseId54(accountDTO.getAmount());
        String caseId58 = getCaseId58(accountDTO.getCountry());
        String caseId62 = getCaseId62(accountDTO.getPurposeOfTransaction());

        String vietQrString = caseId00 + caseId01 + caseId38 + caseId53 + caseId54 + caseId58 + caseId62;
        String caseId63 = getCaseId63(vietQrString);

        return vietQrString + caseId63;
    }

    @Override
    public void generateImageVietQR(Type type, AccountDTO accountDTO) {
        try {
            String data = getVietQRCode(type, accountDTO);
            String path = "D:/vietQR.jpg";
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", Paths.get(path));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private String getCaseId00() {
        return "000201";
    }

    private String getCaseId01(Type type) {
        if (type == Type.DYNAMIC) {
            return "010212";
        } else if (type == Type.STATIC) {
            return "010211";
        }
        return "";
    }

    private String getCaseId38(String accountNo) {
        final String caseId3800 = "A000000727";
        String lengthCaseId3800 = padLength(caseId3800.length());

        final String caseId380100 = "970403";
        final String caseId380101 = accountNo;
        String lengthCaseId380101 = padLength(caseId380101.length());

        String valueCaseId3801 = "0006" + caseId380100 + "01" + lengthCaseId380101 + caseId380101;
        String lengthCaseId3801 = padLength(valueCaseId3801.length());

        String caseId3801 = "01" + lengthCaseId3801 + valueCaseId3801;

        String caseId3802 = "0208QRIBFTTA";

        String valueCaseId38 = "00" + lengthCaseId3800 + caseId3800 + caseId3801 + caseId3802;
        String lengthCaseId38 = padLength(valueCaseId38.length());

        return "38" + lengthCaseId38 + valueCaseId38;
    }

    private String getCaseId53(Country country) {
        switch (country) {
            case VN:
                return "5303704";
            case TH:
                return "5303764";
            case SG:
                return "5303702";
            case RP:
                return "5303608";
            case RI:
                return "5303360";
            case RC:
                return "5303156";
            case MY:
                return "5303458";
            case KR:
                return "5303410";
            case JP:
                return "5303392";
            default:
                return "";
        }
    }

    private String getCaseId54(String amount) {
        if (amount == null || amount.isEmpty()) {
            return "";
        }
        String lengthAmount = padLength(amount.length());
        return "54" + lengthAmount + amount;
    }

    private String getCaseId58(Country country) {
        return "58" + "02" + country;
    }

    private String getCaseId62(String purposeOfTransaction) {
        String lengthCaseId62 = padLength((4+purposeOfTransaction.length()));
        String lengthCaseId6208 = padLength(purposeOfTransaction.length());
        return "62" + lengthCaseId62 + "08" + lengthCaseId6208+purposeOfTransaction;
    }

    private String getCaseId63(String vietQrString) {
        return "6304" + Helper.convertToCRC16(vietQrString+"6304");
    }

    private String padLength(int length) {
        return length < 10 ? "0" + length : String.valueOf(length);
    }

}
