package com.bill.view;

import com.bill.enums.NotificationEnum;
import com.bill.kafka.MessagePacket;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TelegramOtpCode extends BaseOtpCode {
    @NotBlank(message = "type cannot be empty")
    @Schema(description = "type", required = true, example = "3")
    private String type;

    @Schema(description = "otp", required = false, example = "")
    private String otp;

    public MessagePacket toPacket(String otpCode) { return new MessagePacket(NotificationEnum.TELEGRAM, otpCode, ""); }
}

