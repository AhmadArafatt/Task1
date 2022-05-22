package com.test.testpro.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Data
@Primary
@Component
@ConfigurationProperties(prefix = "details")
public class UserConfig {
    String host;
    String port;
}
