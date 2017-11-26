package lab6.rest.pojo;


import java.util.List;

public class EntryPOJO {
    private int id;
    private String date;
    private String subject; //temat wpisu
    private String content; //treść wpisu
    private List<CommentPOJO> comments;

    public EntryPOJO() {
    }

    public EntryPOJO(int id, String date, String subject, String content, List<CommentPOJO> comments) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.content = content;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentPOJO> getComments() {
        return comments;
    }

    public void setComments(List<CommentPOJO> comments) {
        this.comments = comments;
    }
}
