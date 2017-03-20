package es.client.test.es;


import javafx.scene.chart.PieChart;

/**
 * Created by hadoop on 2017/03/11.
 */
public class Article {
    private long id;
    private String title;
    private String time;
    private String content;
    private String url;

    public Article() {
    }

    public Article(long id, String title, String time, String content, String url) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.content = content;
        this.url = url;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
