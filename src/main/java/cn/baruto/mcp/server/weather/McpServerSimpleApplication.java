package cn.baruto.mcp.server.weather;

import cn.baruto.mcp.server.weather.properties.WeatherApiProperties;
import cn.baruto.mcp.server.weather.service.WeatherService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class McpServerSimpleApplication implements CommandLineRunner {

    @Resource
    private WeatherApiProperties weatherApiProperties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerSimpleApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(WeatherService weatherService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("check api key ...");
        if (weatherApiProperties.getApiKey() == null) {
            log.warn("weather api key is null, please set it in application.yml");
        } else {
            log.info("weather api key is {}", weatherApiProperties.getApiKey());
        }
    }
}
