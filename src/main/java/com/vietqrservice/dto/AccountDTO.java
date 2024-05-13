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
    private String addInfo;
    private Country country;
}
