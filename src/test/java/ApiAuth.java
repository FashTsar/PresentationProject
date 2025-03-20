import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.Base64;

import static io.restassured.RestAssured.given;

public class ApiAuth {
    private DataProperties dataProperties;

    public String bodyAuth() {
        JSONObject body = new JSONObject()
                .put("username", dataProperties.getDataProperties("config.properties","login"))
                .put("password", Base64.getEncoder().encodeToString(dataProperties.getDataProperties("config.properties","password").getBytes()));
        return body.toString();
    }

    public String auth() {
        dataProperties = new DataProperties();

        // Настройка времени ожидания запроса до 20 минут
        HttpClientConfig httpConfig = HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 1200000).setParam("http.socket.timeout", 1200000);
        RestAssuredConfig config = RestAssuredConfig.config().httpClient(httpConfig);

        String AuthTokenResponse = given().config(config)
                .when()
                //.log().all()
                .body(bodyAuth())
                .contentType(ContentType.JSON)
                .post(dataProperties.getDataProperties("config.properties" ,"api.stand")+dataProperties.getDataProperties("pens.properties" ,"login"))
                .then()
                //.log().all()
                .extract()
                .body()
                .asString();

        // Находим начало подстроки после ": "
        int startIndex = AuthTokenResponse.indexOf(": ") + 2;

        // Находим конец подстроки перед "="
        int endIndex = AuthTokenResponse.indexOf("\"", startIndex);

        return AuthTokenResponse.substring(startIndex, endIndex);
    }
}
