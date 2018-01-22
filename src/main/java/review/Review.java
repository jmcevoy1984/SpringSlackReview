package reviews;

public class Review {
    private String userName;
    private String callId;
    private String rating;
    private String comment;

    public Review() {
        this.userName = null;
        this.callId = null;
        this.rating = null;
        this.comment = null;
    }

    public Review(String userName, String callId, String rating, String comment) {
        this.userName = userName;
        this.callId = callId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getUserName() { return userName; }

    public void setUserName(String newUserName) { this.userName = newUserName; }

    public String getCallId() { return callId; }

    public void setCallId(String newCallId) { this.callId = newCallId; }

    public String getRating() { return rating; }

    public void setRating(String newRating) { this.rating = newRating; }

    public String getComment() { return comment; }

    public void setComment(String newComment) { this.comment = newComment; }
}