package com.bill.view;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailMessage extends BaseMessage {
    @NotBlank(message = "type cannot be empty")
    @Schema(description = "type", required = true, example = "2")
    private String type;

    @NotBlank(message = "message cannot be empty")
    @Schema(description = "message", required = true, example = "test message")
    private String message;

    @NotBlank(message = "to cannot be empty")
    @Schema(description = "to", required = true, example = "jbbillchou@gmail.com")
    private String to;
}

