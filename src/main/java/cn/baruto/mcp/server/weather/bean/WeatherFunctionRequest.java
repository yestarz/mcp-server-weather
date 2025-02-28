package cn.baruto.mcp.server.weather.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherFunctionRequest {
    @JsonProperty(required = true, value = "city")
    @JsonPropertyDescription("城市名称,如果是中文汉字请先转换为汉语拼音,例如北京:beijing")
    private String city;
}
