package helpers;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    public static String getVideoUrl(String sessionId) throws InterruptedException {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        Thread.sleep(15000);
        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic("asdasdqwdffsfdwe_FJixVj", "Lstx5wXmrYFxG5o5G46S")
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
