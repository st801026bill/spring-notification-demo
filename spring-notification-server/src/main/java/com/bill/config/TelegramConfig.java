package com.bill.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class TelegramConfig {
  @Value("${telegram.url}")
  private String url;
  @Value("${telegram.apiToken}")
  private String apiToken;
  @Value("${telegram.id}")
  private String id;
}
