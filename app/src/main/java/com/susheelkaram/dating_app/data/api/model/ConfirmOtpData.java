package com.susheelkaram.dating_app.data.api.model;

/**
 * Created by Susheel Kumar Karam
 * Website - SusheelKaram.com
 */
public class ConfirmOtpData {
    String number;
    String otp;

    public ConfirmOtpData(String number, String otp) {
        this.number = number;
        this.otp = otp;
    }
}
