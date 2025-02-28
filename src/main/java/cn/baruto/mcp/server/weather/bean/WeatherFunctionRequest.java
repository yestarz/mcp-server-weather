package cn.baruto.mcp.server.weather.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherFunctionRequest {
    @JsonProperty(required = true, value = "city")
    @JsonPropertyDescription("城市，例如：北京")
    private String city;
}
