package com.bill.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum NotificationEnum {

    SMS("1"),
    EMAIL("2"),
    TELEGRAM("3");

    private String type;
    NotificationEnum(String type) {
        this.type = type;
    }

    private static final Map<String, NotificationEnum> lookup = new HashMap<String, NotificationEnum>();

    static {
        Arrays.stream(NotificationEnum.values()).forEach(e -> lookup.put(e.type, e));
    }

    public static NotificationEnum findByType(String type) {
        return lookup.get(type);
    }
}
