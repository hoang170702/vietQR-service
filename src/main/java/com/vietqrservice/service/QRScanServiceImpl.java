package com.vietqrservice.service;

import com.vietqrservice.entity.VietQR;
import com.vietqrservice.repository.VietQRRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class QRScanServiceImpl implements QRScanService {
    @Autowired
    private VietQRRepository vqrRepository;

    @Override
    public VietQR dataVietQR(String QRString) {
        try {
            int totalLength = QRString.length();
            VietQR vietQR = new VietQR();
            String id = QRString.substring(0, 2);
            String length = QRString.substring(2, 4);
            int contentLength = Integer.parseInt(length);
            while (totalLength > 0) {
                String content = QRString.substring(id.length() + length.length(), id.length() + length.length() + contentLength);
                String cutPart = id + length + content;
                checkKey(id, contentLength, content, QRString, vietQR);
                totalLength -= cutPart.length();
                QRString = QRString.substring(cutPart.length());
                if (totalLength >= 4) {
                    id = QRString.substring(0, 2);
                    length = QRString.substring(2, 4);
                    contentLength = Integer.parseInt(length);
                }
            }
            return vietQR;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void checkKey(String id, int length, String content, String QRString, VietQR vietQR) {
        switch (id) {
            case "00":
                if (length == 2) {
                    vietQR.setPayloadFormatIndicator(content);
                }
                break;
            case "01":
                if (length == 2) {
                    vietQR.setPointOfInitiationMethod(content);
                }
                break;
            case "02":
            case "03":
                vietQR.setVisa(content);
                break;
            case "04":
            case "05":
                vietQR.setMasterCard(content);
                break;
            case "06":
            case "07":
            case "08":
                vietQR.setEMVCo(content);
                break;
            case "09":
            case "10":
                vietQR.setDiscover(content);
                break;
            case "11":
            case "12":
                vietQR.setAmex(content);
                break;
            case "13":
            case "14":
                vietQR.setJcb(content);
                break;
            case "15":
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
            case "21":
            case "22":
            case "23":
            case "24":
            case "25":
                vietQR.setUnionPay(content);
                break;
            case "38":
                if (length <= 99) {
                    vietQR.setMerchantAccountInformation(content);
                    handleCase38(content, vietQR);
                }
                break;
            case "52":
                if (length == 4) {
                    vietQR.setMerchantCategoryCode(content);
                }
                break;
            case "53":
                if (length == 3) {
                    vietQR.setTransactionCurrency(content);
                }
                break;
            case "54":
                if (length <= 13) {
                    vietQR.setTransactionAmount(content);
                }
                break;
            case "55":
                if (length == 2) {
                    vietQR.setTipOrConvenienceIndicator(content);
                }
                break;
            case "56":
                if (length <= 13) {
                    vietQR.setValueOfConvenienceFeeFixed(content);
                }
                break;
            case "57":
                if (length <= 5) {
                    vietQR.setValueOfConvenienceFeePercentage(content);
                }
                break;
            case "58":
                if (length == 2) {
                    vietQR.setCountryCode(content);
                }
                break;
            case "59":
                if (length <= 25) {
                    vietQR.setMerchantName(content);
                }
                break;
            case "60":
                if (length <= 15) {
                    vietQR.setMerchantCity(content);
                }
                break;
            case "61":
                if (length <= 10) {
                    vietQR.setPostalCode(content);
                }
                break;
            case "62":
                if (length <= 99) {
                    vietQR.setAdditionalDataFieldTemplate(content);
                }
                break;
            case "64":
                if (length <= 99) {
                    vietQR.setMerchantInformationLanguageTemplate(content);
                }
                break;
            case "65":
            case "66":
            case "67":
            case "68":
            case "69":
            case "70":
            case "71":
            case "72":
            case "73":
            case "74":
            case "75":
            case "76":
            case "77":
            case "78":
            case "79":
                if (length <= 99) {
                    vietQR.setRfuForEMVCo(content);
                }
                break;
            case "80":
            case "81":
            case "82":
            case "83":
            case "84":
            case "85":
            case "86":
            case "87":
            case "88":
            case "89":
            case "90":
            case "91":
            case "92":
            case "93":
            case "94":
            case "95":
            case "96":
            case "97":
            case "98":
            case "99":
                if (length <= 99) {
                    vietQR.setUnreservedTemplates(content);
                }
                break;
            case "63":
                if (length <= 99) {
                    vietQR.setCrc(content);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void handleCase38(String content, VietQR vietQR) {
        try {
            int totalLength = content.length();
            while (totalLength > 0) {
                String id = content.substring(0, 2);
                String length = content.substring(2, 4);
                int subContentLength = Integer.parseInt(length);
                String subContent = content.substring(4, 4 + subContentLength);
                if (id.equals("00")) {
                    vietQR.setGuid(subContent);
                } else if (id.equals("01")) {
                    while (subContent.length() > 0) {
                        String subId = subContent.substring(0, 2);
                        String subLength = subContent.substring(2, 4);
                        int subLengthValue = Integer.parseInt(subLength);
                        String subSubContent = subContent.substring(4, 4 + subLengthValue);

                        if (subId.equals("00")) {
                            vietQR.setAcquierIdBnbId(subSubContent);
                        } else if (subId.equals("01")) {
                            vietQR.setMerchantIdConsumerId(subSubContent);
                        }
                        subContent = subContent.substring(4 + subLengthValue);
                    }
                } else if (id.equals("02")) {
                    vietQR.setServiceId(subContent);
                }
                totalLength -= (4 + subContentLength);
                content = content.substring(4 + subContentLength);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void save(VietQR vietQR) {
        try {
            this.vqrRepository.save(vietQR);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<VietQR> getAllVietQRs() {
        try {
            return this.vqrRepository.findAll();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return Collections.emptyList();
    }
}
