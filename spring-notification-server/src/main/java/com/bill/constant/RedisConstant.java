package com.bill.constant;

public class RedisConstant {
    private static final String SMS = "SMS";
    private static final String MAIL = "MAIL";
    private static final String TELEGRAM = "TELEGRAM";

    public static final Long EXIPIRE_TIME = 5 * 60L;

    public static String getSmsKey(String phoneNo) { return String.format("%s_%s_KEY", SMS, phoneNo); }
    public static String getMailKey(String mail) { return String.format("%s_%s_KEY", MAIL, mail); }
    public static String getTelegramKey() { return String.format("%s_KEY", TELEGRAM); }

    public static String getSmsBlockKey(String phoneNo) { return String.format("%s_%s_BLOCK_KEY", SMS, phoneNo); }
    public static String getMailBlockKey(String mail) { return String.format("%s_%s_BLOCK_KEY", MAIL, mail); }
    public static String getTelegramBlockKey() { return String.format("%s_BLOCK_KEY", TELEGRAM); }
}
