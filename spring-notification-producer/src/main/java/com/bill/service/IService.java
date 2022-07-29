package com.bill.service;

public interface IService<Message, OtpCode> {
    void sendMessage(Message view);
    void sendOtpCode(OtpCode view, String otp);
    Boolean verifyOtpCode(OtpCode view);
}
