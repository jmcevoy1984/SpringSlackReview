package reviews;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.ObjectMapper;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Map;
import java.util.HashMap;

// Sending Slack messages via incoming webhooks detailed here:
// https://api.slack.com/incoming-webhooks
public class MessageSender{
    private String slackWebhookURL;

    public MessageSender(String URL){ this.slackWebhookURL = URL; }

    public Map<String, Object> postToSlack(Object message) throws Exception {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                        = new com.fasterxml.jackson.databind.ObjectMapper();
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        HttpResponse<String> response = Unirest.post(slackWebhookURL)
                .header("Content-Type", "application/json")
                .body(message)
                .asString();
        int status = response.getStatus();
        String body = response.getBody().toString();
        Unirest.shutdown();
        Map<String, Object> slackResponse = new HashMap<String, Object>();
        slackResponse.put("status", status);
        slackResponse.put("message", body);
        return slackResponse;
    }
}