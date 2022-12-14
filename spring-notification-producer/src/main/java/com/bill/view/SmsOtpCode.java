package com.bill.view;

import com.bill.enums.NotificationEnum;
import com.bill.kafka.MessagePacket;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SmsOtpCode extends BaseOtpCode {
    @NotBlank(message = "type cannot be empty")
    @Schema(description = "type", required = true, example = "1")
    private String type;

    @NotBlank(message = "to cannot be empty")
    @Schema(description = "to", required = true, example = "+886972043271")
    private String to;

    @Schema(description = "otp", required = false, example = "")
    private String otp;

    public MessagePacket toPacket(String otpCode) { return new MessagePacket(NotificationEnum.SMS, otpCode, this.to); }
}

