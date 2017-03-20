package es.client.test.es;

import org.elasticsearch.common.inject.internal.Nullability;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 2017/03/11.
 */
import es.client.test.es.Article;
import es.client.test.es.JsonUtil;
import es.client.test.es.ReadFile;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class DataFactory {
    public static DataFactory dataFacetory = new DataFactory();

    private DataFactory() {
    }

    public DataFactory getInstance() {
        return dataFacetory;
    }

    public static List<XContentBuilder> getInitJsonData(String path, int n) throws Exception {
        if(path.equals("")) {
            path = "/home/hadoop/develop/testFile/test3";
        }

        ArrayList list = new ArrayList();
        File[] fa = ReadFile.getFileName(path);
        boolean m = false;
        int var8;
        if(fa.length > n) {
            var8 = n;
        } else {
            var8 = fa.length;
        }

        for(long i = 0L; i <= (long)var8; ++i) {
            Article temp = ReadFile.readFile(i, fa[(new Long(i)).intValue()].toString());
            list.add(JsonUtil.model2Json(temp));
            System.out.println("-------:" + fa[(new Long(i)).intValue()].toString());
        }

        return list;
    }

    public static List<XContentBuilder> getInitJsonWord(String path) throws Exception {
        if(path.equals("")) {
            path = "/home/hadoop/develop/testFile/w4";
        }

        ArrayList listWord = new ArrayList();
        List Wordtemp = ReadFile.readWord(path);
        listWord.add(JsonUtil.word2Json(Wordtemp));
        return listWord;
    }

    public static List<XContentBuilder> getInitJsonSuggestWord(String path) throws Exception {
        if(path.equals("")) {
            path = "/home/hadoop/develop/testFile/w4";
        }

        ArrayList listWord = new ArrayList();
        List Wordtemp = ReadFile.readWord(path);

        for(int i = 0; i < Wordtemp.size(); ++i) {
            listWord.add(JsonUtil.word2Json(Wordtemp));
        }

        return listWord;
    }
}