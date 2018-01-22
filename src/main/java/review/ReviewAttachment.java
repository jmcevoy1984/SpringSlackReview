package reviews;

import java.util.Map;
import java.util.HashMap;

public class ReviewAttachment {
    private Attachment attachment;

    public ReviewAttachment(Review review){
        this.attachment = new Attachment();
        String ratingValue = review.getRating();
        String title = "New Rating From: " + "\"" + review.getUserName() +"\"";
        String fallback = "New Rating From: " + "_\"" + review.getUserName() + "   Rating: " + ratingValue + "/5";
        String color = null;
        if (ratingValue.equals("5")) {
            color = "#6d4887"; //Phorest Purple
        }
        else if (ratingValue.equals("2")) {
            color = "#ff7f00";
        }
        else if (ratingValue.equals("1")) {
            color = "danger";
        }
        else if (ratingValue.equals("4")) {
            color = "good";
        }
        Map<String, Object> space = new HashMap<String, Object>();
        space.put("title", "");
        space.put("value", "");
        space.put("short", false);
        Map<String, Object> comment = new HashMap<String, Object>();
        comment.put("title", "Comment:");
        comment.put("value", "_\""+review.getComment()+"\"_");
        comment.put("short", false);
        Map<String, Object> callId = new HashMap<String, Object>();
        callId.put("title", "Call ID:");
        callId.put("value", review.getCallId());
        callId.put("short", true);
        Map<String, Object> rating = new HashMap<String, Object>();
        rating.put("title", "Rating:");
        rating.put("value", ratingValue);
        rating.put("short", true);
        this.attachment.addField(space);
        this.attachment.addField(callId);
        this.attachment.addField(rating);
        this.attachment.addField(comment);
        this.attachment.setFallback(fallback);
        this.attachment.setTitle(title);
        this.attachment.setColor(color);
        this.attachment.addToMrkdwnIn("fields");
        this.attachment.addToMrkdwnIn("text");
    }

    public Attachment getAttachment() {
        return attachment;
    }
}