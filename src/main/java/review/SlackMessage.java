package reviews;

import java.util.Map;
import java.util.ArrayList;

public class SlackMessage {
    private String text;
    private ArrayList<Attachment> attachments;

    public String getText() { return text; }

    public void setText(String newText) { this.text = newText; }

    public void addAttachment(Attachment newAttachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<Attachment>();
        }
        this.attachments.add(newAttachment);
    }

    public ArrayList<Attachment> getAttachments() { return this.attachments; }
}