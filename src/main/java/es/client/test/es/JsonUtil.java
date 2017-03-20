package es.client.test.es;

import es.client.test.es.Article;
import java.util.List;
import org.elasticsearch.action.fieldstats.FieldStats;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by hadoop on 2017/03/11.
 */

public class JsonUtil {
    public JsonUtil() {
    }

    public static XContentBuilder model2Json(Article article) {
        XContentBuilder jsonData = null;

        try {
            jsonData = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("id", article.getId())
                    .field("title", article.getTitle())
                    .field("time", article.getTime())
                    .field("content", article.getContent())
                    .field("url", article.getUrl())
                    .startObject("suggest")
                    .field("input", article.getTitle())
                    .endObject()
                    .endObject();
            System.out.println(jsonData.string());
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return jsonData;
    }

    public static XContentBuilder word2Json(List<String> word) {
        XContentBuilder jsonWord = null;

        try {
            jsonWord = XContentFactory.jsonBuilder()
                    .startObject().field("suggest", word).endObject();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return jsonWord;
    }

    public static XContentBuilder suggestword2Json(String word) {
        XContentBuilder jsonWord = null;

        try {
            jsonWord = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("suggest")
                    .field("input", word)
                    .endObject().endObject();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return jsonWord;
    }
}
