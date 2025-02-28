package cn.baruto.mcp.server.weather.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "weather.api")
@Data
@Component
public class WeatherApiProperties {
    private String apiKey;
}
