package com.vietqrservice.service;

import com.vietqrservice.constant.Country;
import com.vietqrservice.constant.Type;
import com.vietqrservice.dto.AccountDTO;
import com.vietqrservice.utils.Helper;
import org.springframework.stereotype.Service;

@Service
public class QRGenerateServiceImpl implements QRGenerateService {
    @Override
    public int crc16(byte[] buffer) {
        return 0;
    }

    @Override
    public String getCrc16Valid(String vietQRCode) {
        return "";
    }

    @Override
    public String getVietQRCode(Type type, AccountDTO accountDTO) {
        // case 00
        final String CASE_ID_00 = "000201";

        //case 01
        String CASE_ID_01 = "";
        if (type == Type.DYNAMIC) {
            CASE_ID_01 = "010212";
        } else if (type == Type.STATIC) {
            CASE_ID_01 = "010211";
        }

        // case 38
        final String CASE_ID_38_00 = "A000000727";
        String LENGTH_CASE_ID_38_00;
        if (CASE_ID_38_00.length() < 10) {
            LENGTH_CASE_ID_38_00 = "0" + CASE_ID_38_00.length();
        } else {
            LENGTH_CASE_ID_38_00 = "" + CASE_ID_38_00.length();
        }

        final String CASE_ID_38_01_00 = "970403";
        final String CASE_ID_38_01_01 = "2112995044604025";
        String LENGTH_CASE_ID_38_01_01;
        if (CASE_ID_38_01_01.length() < 10) {
            LENGTH_CASE_ID_38_01_01 = "0" + CASE_ID_38_01_01.length();
        } else {
            LENGTH_CASE_ID_38_01_01 = "" + CASE_ID_38_01_01.length();
        }
        final String VALUE_CASE_ID_38_01 = "0006" + CASE_ID_38_01_00 + "01" + LENGTH_CASE_ID_38_01_01 + CASE_ID_38_01_01;
        String LENGTH_CASE_ID_38_01;
        if (VALUE_CASE_ID_38_01.length() < 10) {
            LENGTH_CASE_ID_38_01 = "0" + VALUE_CASE_ID_38_01.length();
        } else {
            LENGTH_CASE_ID_38_01 = "" + VALUE_CASE_ID_38_01.length();
        }
        final String CASE_ID_38_01 = "01" + LENGTH_CASE_ID_38_01 + VALUE_CASE_ID_38_01;

        final String CASE_ID_38_02 = "02" + "08" + "QRIBFTTC";


        final String VALUE_CASE_ID_38 = "00" + LENGTH_CASE_ID_38_00 + CASE_ID_38_00 + CASE_ID_38_01 + CASE_ID_38_02;
        String LENGTH_CASE_ID_38;
        if (VALUE_CASE_ID_38.length() < 10) {
            LENGTH_CASE_ID_38 = "0" + VALUE_CASE_ID_38.length();
        } else {
            LENGTH_CASE_ID_38 = "" + VALUE_CASE_ID_38.length();
        }

        final String CASE_ID_38 = "38" + LENGTH_CASE_ID_38 + VALUE_CASE_ID_38;
        // end case 38

        //case 52
        final String CASE_ID_52 = "52045137";

        // case 53
        String CASE_ID_53 = "";
        if (accountDTO.getCountry() == Country.VN) {
            CASE_ID_53 = "5303" + "704";
        } else if (accountDTO.getCountry() == Country.TH) {
            CASE_ID_53 = "5303" + "764";
        } else if (accountDTO.getCountry() == Country.SG) {
            CASE_ID_53 = "5303" + "702";
        } else if (accountDTO.getCountry() == Country.RP) {
            CASE_ID_53 = "5303" + "608";
        } else if (accountDTO.getCountry() == Country.RI) {
            CASE_ID_53 = "5303" + "360";
        } else if (accountDTO.getCountry() == Country.RC) {
            CASE_ID_53 = "5303" + "156";
        } else if (accountDTO.getCountry() == Country.MY) {
            CASE_ID_53 = "5303" + "458";
        } else if (accountDTO.getCountry() == Country.KR) {
            CASE_ID_53 = "5303" + "410";
        } else if (accountDTO.getCountry() == Country.JP) {
            CASE_ID_53 = "5303" + "392";
        }

        // case 54
        String CASE_ID_54 = "54" + "0" + accountDTO.getAmount().length() + accountDTO.getAmount();

        //case 58
        final String CASE_ID_58 = "5802" + accountDTO.getCountry();

        //case 59
        String LENGTH_CASE_ID_59;
        if (accountDTO.getAddInfo().length() < 10) {
            LENGTH_CASE_ID_59 = "0" + accountDTO.getAddInfo().length();
        }else {
            LENGTH_CASE_ID_59 = "" + accountDTO.getAddInfo().length();
        }
        final String CASE_ID_59 = "59"+LENGTH_CASE_ID_59 + accountDTO.getAddInfo();

        //vietQR
        final String vietQrString = CASE_ID_00 + CASE_ID_01 + CASE_ID_38 + CASE_ID_52 + CASE_ID_53 + CASE_ID_54 + CASE_ID_58+ CASE_ID_59;

        //case63
        final String CASE_ID_63 = "6304" + Helper.convertToCRC(vietQrString);

        return vietQrString + CASE_ID_63;
    }


}
