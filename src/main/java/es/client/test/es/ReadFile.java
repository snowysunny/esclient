package es.client.test.es;

import java.io.*;

/**
 * Created by hadoop on 2017/03/11.
 */
import es.client.test.es.Article;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadFile {
    public ReadFile() {
    }

    public static File[] getFileName(String path) {
        File f = new File(path);
        if(!f.exists()) {
            System.out.println(path + " not exists");
            return null;
        } else {
            File[] fa = f.listFiles();
            return fa;
        }
    }

    public static List<String> readWord(String path) throws Exception {
        ArrayList list = new ArrayList();
        InputStreamReader ir = null;
        BufferedReader br = null;
        FileInputStream inputStream = new FileInputStream(path);
        ir = new InputStreamReader(inputStream);
        br = new BufferedReader(ir);
        String line = null;
        String[] words = null;

        while((line = br.readLine()) != null) {
            words = line.split("\t");
            list.add(words[0]);
        }

        return list;
    }

    public static Article readFile(long id, String filename) throws Exception {
        InputStreamReader ir = null;
        BufferedReader br = null;
        Object outputStream = null;
        FileInputStream inputStream = new FileInputStream(filename);
        ir = new InputStreamReader(inputStream);
        br = new BufferedReader(ir);
        String time = null;
        String title = null;
        String content = null;
        String url = null;
        String line = null;
        StringBuffer strBuffer = new StringBuffer();

        for(int index = 0; (line = br.readLine()) != null; ++index) {
            if(index == 0) {
                url = line;
            } else if(index == 1) {
                title = line;
            } else if(index == 2) {
                SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                time = a.format(date);
            } else {
                strBuffer.append(line + "\n");
            }
        }

        content = strBuffer.toString();
        Article var16 = new Article(id, title, time, content, url);
        ir.close();
        br.close();
        return var16;
    }
}