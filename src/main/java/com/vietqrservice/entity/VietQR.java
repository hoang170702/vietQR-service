package com.vietqrservice.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "viet_qr")
public class VietQR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payload_format")
    private String payloadFormatIndicator;

    @Column(name = "init_method")
    private String pointOfInitiationMethod;

    @Column(name = "visa")
    private String visa;

    @Column(name = "mastercard")
    private String masterCard;

    @Column(name = "EMVCo")
    private String EMVCo;

    @Column(name = "discover")
    private String discover;

    @Column(name = "amex")
    private String amex;

    @Column(name = "jcb")
    private String jcb;

    @Column(name = "union_pay")
    private String unionPay;

    @Column(name = "merchant_account_information")
    private String merchantAccountInformation;

    @Column(name = "merchant_category_code")
    private String merchantCategoryCode;

    @Column(name = "transaction_currency")
    private String transactionCurrency;

    @Column(name = "transaction_amount")
    private String transactionAmount;

    @Column(name = "tip_or_convenience_indicator")
    private String tipOrConvenienceIndicator;

    @Column(name = "value_of_convenience_fee_fixed")
    private String valueOfConvenienceFeeFixed;

    @Column(name = "value_of_convenience_fee_percentage")
    private String valueOfConvenienceFeePercentage;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_city")
    private String merchantCity;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "additional_data_field_template")
    private String additionalDataFieldTemplate;

    @Column(name = "merchant_information_language_template")
    private String merchantInformationLanguageTemplate;

    @Column(name = "RFU_for_emvCo")
    private String rfuForEMVCo;

    @Column(name = "unreserved_templates")
    private String unreservedTemplates;

    @Column(name = "crc")
    private String crc;

    @Column(name = "guid")
    private String guid;

    @Column(name = "Acquier_ID_BNB_ID")
    private String acquierIdBnbId;

    @Column(name = "Merchant_ID_Consumer_ID")
    private String merchantIdConsumerId;

    @Column(name = "Service_id")
    private String serviceId;

}
