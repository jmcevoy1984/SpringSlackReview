package reviews;


import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Attachment {
    
    private String fallback;
    private String color;
    private String pretext;
    private String author_name;
    private String author_link;
    private String author_icon;
    private String title;
    private String title_link;
    private String text;
    private String image_url;
    private String thumb_url;
    private String footer;
    private String footer_icon;
    private int ts;
    private ArrayList<Map<String, Object>> fields;
    private ArrayList<String> mrkdwn_in;

    private Map<String, Object> validKeysAndValuesForFields = new HashMap<String, Object>()
        {{
            put("title", "");
            put("value", "");
            put("short", true);
        }};

    private ArrayList<String> allowedMrkdwnInFields = new ArrayList<String>(Arrays.asList("text", "pretext", "fields"));


    private boolean isFieldValid(Map<String, Object> newField) {

        ArrayList<String> alreadyCheckedKeys = new ArrayList<String>();

        for(Map.Entry<String, Object> validField:this.validKeysAndValuesForFields.entrySet()) {

            if(newField.get(validField.getKey()) != null) {

                if(!(newField.get(validField.getKey()).getClass().equals(validField.getValue().getClass()))) {

                    return false;

                }
                else {

                    if (alreadyCheckedKeys.contains(validField.getKey())) {


                        return false;

                    }
                    else{

                        alreadyCheckedKeys.add(validField.getKey());

                    }
                    
                }

            }
            else {

                return false;

            }

        }

        return true;

    }

    public String getFallback() {

        return fallback;

    }

    public String getColor() {

        return color;

    }

    public String getPretext() {

        return pretext;

    }

    public String getAuthor_name() {

        return author_name;

    }

    public String getAuthor_link() {

        return author_link;

    }

    public String getAuthor_icon() {

        return author_icon;

    }

    public String getTitle() {

        return title;

    }

    public String getTitle_link() {

        return title_link;

    }

    public String getText() {

        return text;

    }

    public ArrayList<Map<String, Object>> getFields() {

        return fields;

    }

    public String getImage_url() {

        return image_url;

    }

    public String getThumb_url() {

        return thumb_url;

    }

    public String getFooter() {

        return footer;

    }

    public String getFooter_icon() {

        return footer_icon;

    }

    public int getTs() {

        return ts;

    }

    public ArrayList<String> getMrkdwn_in() {

        return mrkdwn_in;

    }

    public void setFallback(String newFallback) {

        this.fallback = newFallback;

    }

    public void setColor(String newColor) {

        this.color = newColor;

    }

    public void setPretext(String newPretext) {

        this.pretext = newPretext;

    }

    public void setAuthorName(String newAuthorName) {

        this.author_name = newAuthorName;

    }

    public void setAuthorLink(String newAuthorLink) {

        this.author_link = newAuthorLink;

    }

    public void setAuthorIcon(String newAuthorIcon) {

        this.author_icon = newAuthorIcon;

    }

    public void setTitle(String newTitle) {

        this.title = newTitle;

    }

    public void setTitleLink(String newTitleLink) {

        this.title_link = newTitleLink;

    }

    public void setText(String newText) {

        this.text = newText;

    }

    public void setImageUrl(String newImageUrl) {

        this.image_url = newImageUrl;

    }

    public void setThumbUrl(String newThumbUrl) {

        this.thumb_url = newThumbUrl;

    }

    public void setFooter(String newFooter) {

        this.footer = newFooter;

    }

    public void setFooterIcon(String newFooterIcon) {

        this.footer_icon = newFooterIcon;

    }

    public void setTs(int newTs) {

        this.ts = newTs;

    }

    public void addField(Map<String, Object> newField) {

        if (this.fields == null) {

            this.fields = new ArrayList<Map<String, Object>>();

        }

        if (this.isFieldValid(newField)) {

            this.fields.add(newField);

        }

    }

    public void addToMrkdwnIn(String newMrkdwnInField) {

        if (this.mrkdwn_in == null) {

            this.mrkdwn_in = new ArrayList<String>();

        }

        if (allowedMrkdwnInFields.contains(newMrkdwnInField)) {

            this.mrkdwn_in.add(newMrkdwnInField);

        }

    }

}