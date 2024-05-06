package com.vietqrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
public class VietqrServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VietqrServiceApplication.class, args);
        //String QRString = "0002010102111531397007040052044600006789177200238550010A000000727012500069704230111678917720020208QRIBFTTA5204513753037045802VN5405200005914NGUYEN HUU HOA6006Ha Noi630469A3";
        //cutString(QRString);


    }

    public static void cutString(String QRString) {
        int totalLength = QRString.length();
        String id = QRString.substring(0, 2);
        String length = QRString.substring(2, 4);
        int contentLength = Integer.parseInt(length);
        while (totalLength > 0) {
            String content = QRString.substring(id.length() + length.length(), id.length() + length.length() + contentLength);
            String cutPart = id + length + content;
            checkKey(id, contentLength, content);
            totalLength -= cutPart.length();
            QRString = QRString.substring(cutPart.length());
            if (totalLength >= 4) {
                id = QRString.substring(0, 2);
                length = QRString.substring(2, 4);
                contentLength = Integer.parseInt(length);
            }
        }
    }

    public static void checkKey(String id, int length, String content) {
        String strTest = "";
        switch (id) {
            case "00":
                if (length == 2) {
                    strTest = "Payload Format Indicator:" + content;
                }
                break;
            case "01":
                if (length == 2) {
                    strTest = "Point of Initiation Method:" + content;
                }
                break;
            case "02":
            case "03":
                strTest = "Visa:" + content;
                break;
            case "04":
            case "05":
                strTest = "Mastercard:" + content;
                break;
            case "06":
            case "07":
            case "08":
                strTest = "EMVCo:" + content;
                break;
            case "09":
            case "10":
                strTest = "Discover:" + content;
                break;
            case "11":
            case "12":
                strTest = "Amex:" + content;
                break;
            case "13":
            case "14":
                strTest = "JCB:" + content;
                break;
            case "15":
            case "16":
                strTest = "UnionPay:" + content;
                break;
            case "17":
            case "18":
            case "19":
            case "20":
            case "21":
            case "22":
            case "23":
            case "24":
            case "25":
                strTest = "UnionPay:" + content;
                break;

            case "38":
                cutString38(content);
                break;
            case "52":
                if (length == 4) {
                    strTest = "Merchant Category Code:" + content;
                }
                break;
            case "53":
                if (length == 3) {
                    strTest = "Transaction Currency:" + content;
                }
                break;
            case "54":
                if (length <= 13) {
                    strTest = "Transaction Amount:" + content;
                }
                break;
            case "55":
                if (length == 2) {
                    strTest = "Tip or Convenience Indicator:" + content;
                }
                break;
            case "56":
                if (length <= 13) {
                    strTest = "Value of Convenience Fee Fixed:" + content;
                }
                break;
            case "57":
                if (length <= 5) {
                    strTest = "Value of Convenience Fee Percentage:" + content;
                }
                break;
            case "58":
                if (length == 2) {
                    strTest = "Country Code:" + content;
                }
                break;
            case "59":
                if (length <= 25) {
                    strTest = "Merchant Name:" + content;
                }
                break;
            case "60":
                if (length <= 15) {
                    strTest = "Merchant City:" + content;
                }
                break;
            case "61":
                if (length <= 10) {
                    strTest = "Postal Code:" + content;
                }
                break;
            case "62":
                if (length <= 99) {
                    strTest = "Additional Data Field Template:" + content;
                }
                break;
            case "64":
                if (length <= 99) {
                    strTest = "Merchant Information - Language Template:" + content;
                }
                break;
            case "65":
                if (length <= 99) {
                    strTest = "Merchant Information - Language Template:" + content;
                }
                break;
            case "80":
                if (length <= 99) {
                    strTest = "Unreserved Templates:" + content;
                }
                break;
            case "63":
                if (length <= 99) {
                    strTest = "CRC:" + content;
                }
                break;
            default:
                break;
        }
        System.out.println(strTest);
    }

    public static void cutString38(String content) {
        int totalLength = content.length();
        while (totalLength > 0) {
            String id = content.substring(0, 2);
            String length = content.substring(2, 4);
            int subContentLength = Integer.parseInt(length);
            String subContent = content.substring(4, 4 + subContentLength);

            if(id.equals("00")) {
                System.out.println("GUID: " + subContent);
            } else if(id.equals("01")) {
                // Process ID 01 content
                while (subContent.length() > 0) {
                    String subId = subContent.substring(0, 2);
                    String subLength = subContent.substring(2, 4);
                    int subLengthValue = Integer.parseInt(subLength);
                    String subSubContent = subContent.substring(4, 4 + subLengthValue);

                    if(subId.equals("00")) {
                        System.out.println("ACQ ID/BNB ID: " + subSubContent);
                    } else if(subId.equals("01")) {
                        System.out.println("Merchant ID/Consumer ID: " + subSubContent);
                    }

                    subContent = subContent.substring(4 + subLengthValue);
                }
            } else if(id.equals("02")) {
                System.out.println("Mã dịch vụ: " + subContent);
            }

            totalLength -= (4 + subContentLength);
            content = content.substring(4 + subContentLength);
        }
    }


}
