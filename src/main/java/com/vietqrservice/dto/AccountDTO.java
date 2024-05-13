package com.vietqrservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO implements Serializable {
    private String accountNo;
    private String accountName;
    private String acqId;
    private String amount;
    private String addInfo;
    private String format;
    private String template;
}
