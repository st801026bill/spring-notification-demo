package com.bill.kafka;

import com.bill.enums.NotificationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePacket {
    private NotificationEnum notificationEnum;
    private String message;
    private String to;
}
