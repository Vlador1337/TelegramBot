
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TwitchAPI {
    public String getAccessToken() {
        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("client_id", Const.CLIENT_ID);
        headersMap.put("client_secret", Const.CLIENT_SECRET);
        headersMap.put("grant_type", "client_credentials");
        return given()
                .queryParams(headersMap)
                .post("https://id.twitch.tv/oauth2/token")
                .then().extract().jsonPath().get("access_token");
    }

    public Boolean getLiveStreams() {
        return given()
                .queryParams("query", "Vlador1337", "first", 1)
                .auth()
                .oauth2(getAccessToken())
                .headers("Client-Id", Const.CLIENT_ID)
                .get("https://api.twitch.tv/helix/search/channels")
                .then().log().body().extract().jsonPath().getBoolean("data[0].is_live");
    }
}
