package com.vietqrservice.dto;

import com.vietqrservice.constant.Country;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO implements Serializable {
    private String bankId;
    private String accountNo;
    private String accountName;
    private String amount;
    private String purposeOfTransaction;
    private Country country;

    public String getAmount() {
        return amount != null ? amount : "";
    }

    public String getPurposeOfTransaction() {
        return purposeOfTransaction != null ? purposeOfTransaction : "CHUYEN TIEN NHANH QUA QR";
    }
}
