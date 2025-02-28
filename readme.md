## MCP Server Weather

> 基于和风天气API的一个获取实时天气的MCP服务端，使用Java来实现。和风天气API文档：https://dev.qweather.com/docs/api/weather/weather-now/

## 可用的工具列表

- 获取实时天气 `getWeather`

## 使用方法

1. 下载项目到本地
2. 打包项目，生成jar包 `mvn clean package -Dmaven.test.skip=true`

```json
{
  "mcpServers": {
    "mcp-server-weather": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-jar",
        "你的jar包路径",
        "--weather.api.api-key=YOUR API KEY"
      ]
    }
  }
}
```

## LangChain4J使用方法：

引入依赖：
```xml
     <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-open-ai</artifactId>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-mcp</artifactId>
    </dependency>
```
```java
    /**
     * 初始化MCP Client
     */
    @Bean
    public McpClient mcpClientWeather() {
        return new DefaultMcpClient.Builder()
                .transport(new StdioMcpTransport.Builder()
                        .command(List.of("java", "-Dspring.ai.mcp.server.stdio=true", "-jar", "mcp-server-weather-0.0.1-SNAPSHOT.jar", "--weather.api.api-key=%s".formatted(System.getenv("HEFENG_WEATHER_API_KEY"))))
                        .logEvents(true) // only if you want to see the traffic in the log
                        .build())
                .build();
    }

    /**
     * 使用LangChain4J的高级API来构建一个AI助手，注入MCP Client
     * @param mcpClientWeather
     * @return
     */
    @Bean
    public AiAssistant aiAssistant(McpClient mcpClientWeather) {
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(List.of(mcpClientWeather))
                .build();
        return AiServices.builder(AiAssistant.class)
                .chatLanguageModel(chatLanguageModel())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .toolProvider(toolProvider)
                .build();
    }

    @Test
    public void testWeather1(){
        System.out.println(aiAssistant.chat("今天重庆的天气怎么样？"));
        /**
         * AI回复以下内容：
         * 
         *
         * 今天重庆的天气情况如下：
         - 天气状况：阴
         - 气温：18℃
         - 体感温度：16℃
         - 风向：东风
         - 风力：2级
         - 湿度：47%
         - 降水量：0.0mm
         - 空气压力：980hPa
         - 能见度：9km
         - 云量：91% 
         */
    }
```
