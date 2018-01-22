package reviews;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaT‌​ype;
import org.springframework.http.HttpStatus;
import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

class NullFieldChecker {
    private ObjectMapper mapper = new ObjectMapper();
    private Map<String, Object> objectAsMap;

    public NullFieldChecker(Object object) {
        try {
            this.objectAsMap = this.mapper.convertValue(object, Map.class);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public boolean objectContainsNullFields() {
        for(Map.Entry<String, Object> entry:this.objectAsMap.entrySet()) {
            if(entry.getValue() == null) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getNullHttpResponse() {
        int status = 400;
        String error = "Bad Request";
        String message = "Missing or 'null' value fields.";
        String requiredFields = this.objectAsMap.keySet().toString();
        Map<String, Object> jsonResponse = new HashMap<String, Object>();
        jsonResponse.put("status", status);
        jsonResponse.put("error", error);
        jsonResponse.put("message", message);
        jsonResponse.put("required fields:", requiredFields);
        return ResponseEntity.status(status).body(new JSONObject(jsonResponse).toString());
    }
}

@Controller
@RequestMapping("/review")
public class ReviewController {
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity newReview(@RequestBody Review review) {
        //Any extra fields not present in the Review class will be automatically ignored.
        NullFieldChecker nullFieldChecker = new NullFieldChecker(review);
        if(nullFieldChecker.objectContainsNullFields()) {
            return nullFieldChecker.getNullHttpResponse();
        }
        String slackWebhookURL = "your-webhook-here"; // replace this string with your slack channel webhook
        MessageSender sender = new MessageSender(slackWebhookURL);
        ReviewAttachment reviewAttachment = new ReviewAttachment(review);
        SlackMessage message = new SlackMessage();
        message.addAttachment(reviewAttachment.getAttachment());
        message.addAttachment(reviewAttachment.getAttachment());
        Map<String, Object> replyFromSlack = new HashMap<String, Object>();
        int statusCode = 500;
        replyFromSlack.put("status", statusCode);
        replyFromSlack.put("message", "Failed To Send To Slack");
        replyFromSlack.put("error", "Internal Server Error");
        try {
            replyFromSlack = sender.postToSlack(message);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        for(Map.Entry<String, Object> entry:replyFromSlack.entrySet()) {
            if(entry.getKey().equals("status")) {
                try {
                    statusCode = (int) entry.getValue();
                }
                catch(Exception e) {
                    System.out.println(e);
                }
            }
        }
        return ResponseEntity.status(statusCode).body(new JSONObject(replyFromSlack).toString());
    }
}