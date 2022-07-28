package com.bill.view;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MailOtpCode extends BaseOtpCode {
    @NotBlank(message = "type cannot be empty")
    @Schema(description = "type", required = true, example = "2")
    private String type;

    @NotBlank(message = "to cannot be empty")
    @Schema(description = "to", required = true, example = "jbbillchou@gmail.com")
    private String to;

    @Schema(description = "otp", required = false, example = "")
    private String otp;
}

