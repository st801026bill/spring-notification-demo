package com.bill.sender;

import com.bill.config.TelegramConfig;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TelegramSender {

    @Autowired
    private TelegramConfig telegramConfig;
    private static TelegramConfig TELEGRAM_CONFIG;

    @PostConstruct
    public void init() {
        TELEGRAM_CONFIG = telegramConfig;
    }

    public static void send(String msg) throws IOException{
        String urlString = String.format(TELEGRAM_CONFIG.getUrl(), TELEGRAM_CONFIG.getApiToken(), TELEGRAM_CONFIG.getId(), msg);
        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();
        InputStream is = new BufferedInputStream(conn.getInputStream());
    }
}
