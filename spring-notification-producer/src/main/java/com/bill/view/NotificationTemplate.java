package com.bill.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationTemplate<T> {
    @Valid
    @NotNull(message = "未傳入Header，請確認")
    @JsonProperty("Header")
    private NotificationHeader header;

    @Valid
    @NotNull(message = "未傳入Body，請確認")
    @JsonProperty("Body")
    private T body;
}

