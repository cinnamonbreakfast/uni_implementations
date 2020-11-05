package model;

import java.time.LocalDateTime;

public class Post {
    private Integer id;
    private String user;
    private Integer topicid;
    private String text;
    private LocalDateTime date;

    public Post() {

    }

    public Post(Integer id, String user, Integer topicid, String text, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.topicid = topicid;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':" + id +
                ", 'user':'" + user + '\'' +
                ", 'topicid':" + topicid +
                ", 'text':'" + text + '\'' +
                ", 'date':'" + date + "'" +
                '}';
    }
}
