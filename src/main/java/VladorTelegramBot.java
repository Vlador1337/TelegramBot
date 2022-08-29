import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class VladorTelegramBot {
    public static void main(String[] args) {
        TwitchAPI twitchAPI = new TwitchAPI();
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("chat_id", Const.BOT_ID);
        queryMap.put("text", "Подключайтесь! \uD83E\uDD16 https://www.twitch.tv/vlador1337");
        boolean isMessageDelivered = false;

        try {
            while (true) {
                boolean isAlive;
                isAlive = twitchAPI.getLiveStreams();
                Thread.sleep(10000);

                if (isAlive) {
                    if (!isMessageDelivered) {
                        given()
                                .queryParams(queryMap)
                                .get("https://api.telegram.org/bot" + Const.BOT_TOKEN + "/sendMessage");

                        isMessageDelivered = true;
                    }
                } else {
                    isMessageDelivered = false;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
