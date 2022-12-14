package com.bill.kafka;

import com.bill.enums.NotificationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessagePacket {
    private NotificationEnum notificationEnum;
    private String message;
    private String to;
}
