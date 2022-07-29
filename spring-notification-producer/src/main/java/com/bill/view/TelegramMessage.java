package com.bill.view;

import com.bill.enums.NotificationEnum;
import com.bill.kafka.MessagePacket;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TelegramMessage extends BaseMessage {
    @NotBlank(message = "type cannot be empty")
    @Schema(description = "type", required = true, example = "3")
    private String type;

    @NotBlank(message = "message cannot be empty")
    @Schema(description = "message", required = true, example = "test message")
    private String message;

    public MessagePacket toPacket() { return new MessagePacket(NotificationEnum.TELEGRAM, this.message, ""); }
}

