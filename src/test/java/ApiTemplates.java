import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ApiTemplates {
    private DataProperties dataProperties;
    private ApiAuth apiAuth;

    // гет запрос с телом ответа
    public String getBodyString(String pen) {
        apiAuth = new ApiAuth();
        dataProperties = new DataProperties();

        return given()
                .when()
                //.log().all()
                .contentType(ContentType.JSON)
                .cookie(apiAuth.auth())
                .get(dataProperties.getDataProperties("config.properties", "api.stand") + pen)
                .then()
                //.log().all()
                .extract()
                .body()
                .asString();
    }

    // пост запрос без тела ответа
    public void postStatusCode200(String pen, String body) {
        apiAuth = new ApiAuth();
        dataProperties = new DataProperties();

        given()
                .when()
                //.log().all()
                .body(body)
                .contentType(ContentType.JSON)
                .cookie(apiAuth.auth())
                .post(dataProperties.getDataProperties("config.properties", "api.stand") + pen)
                .then()
                //.log().all()
                .statusCode(200);
    }

    // пост запрос с телом ответа
    public String postBodyString(String pen, String body) {
        apiAuth = new ApiAuth();
        dataProperties = new DataProperties();

        return given()
                .when()
                //.log().all()
                .body(body)
                .contentType(ContentType.JSON)
                .cookie(apiAuth.auth())
                .post(dataProperties.getDataProperties("config.properties", "api.stand") + pen)
                .then()
                //.log().all()
                .extract()
                .body()
                .asString();
    }
}
