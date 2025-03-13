FROM findepi/graalvm:java17

MAINTAINER xinkong

RUN mkdir -p /mcp-weather-server

WORKDIR /mcp-weather-server

ENV SERVER_PORT=8080 LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS=""  TZ=Asia/Shanghai WEATHER_API_KEY=""

EXPOSE 8080

ADD ./target/mcp-server-weather-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-Dspring.ai.mcp.server.stdio=true", "-jar", "app.jar"]
