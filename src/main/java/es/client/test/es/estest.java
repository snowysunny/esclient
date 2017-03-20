package es.client.test.es;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;
import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

public class estest {
    public estest() {
    }

    public static void storeData(TransportClient client, int n) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        List jsonData = DataFactory.getInitJsonData("", n);
        System.out.println(jsonData.size());

        for(int bulkResponse = 0; bulkResponse < jsonData.size(); ++bulkResponse) {
            bulkRequest.add(client.prepareIndex("database", "article", Integer.toString(bulkResponse)).setSource((XContentBuilder)jsonData.get(bulkResponse)));
        }

        BulkResponse var5 = (BulkResponse)bulkRequest.get();
        if(var5.hasFailures()) {
            System.out.println("------------------KKKKKKKKKKKKKKKKKkkkk---" + var5.buildFailureMessage());
            System.out.println("Failed!");
        }

    }

    public static void storeWord(TransportClient client) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        List jsonWord = DataFactory.getInitJsonWord("");
        System.out.println(jsonWord.size());

        for(int bulkResponse = 0; bulkResponse < jsonWord.size(); ++bulkResponse) {
            bulkRequest.add(client.prepareIndex("database", "art_suggest", Integer.toString(bulkResponse)).setSource((XContentBuilder)jsonWord.get(bulkResponse)));
        }

        BulkResponse var4 = (BulkResponse)bulkRequest.get();
        if(var4.hasFailures()) {
            System.out.println("------------------KKKKKKKKKKKKKKKKKkkkk---" + var4.buildFailureMessage());
            System.out.println("Failed!");
        }

    }

    public static void storeSuggestWord(TransportClient client) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        String path = "/home/hadoop/develop/testFile/w4";
        List Wordtemp = ReadFile.readWord(path);
        int num = Wordtemp.size();
        System.out.println("storeSuggestWord" + num);

        for(int bulkResponse = 0; bulkResponse < num; ++bulkResponse) {
            bulkRequest.add(client.prepareIndex("database", "art_suggest").setSource(JsonUtil.suggestword2Json((String)Wordtemp.get(bulkResponse))));
        }

        BulkResponse var6 = (BulkResponse)bulkRequest.get();
        if(var6.hasFailures()) {
            System.out.println("------------------KKKKKKKKKKKKKKKKKkkkk---" + var6.buildFailureMessage());
            System.out.println("Failed!");
        }

    }

    public static void main(String[] args) throws Exception {
        new estest();

        try {
            Settings e = Settings.builder().put("client.transport.sniff", true).put("cluster.name", "es").build();
            TransportClient client = (new PreBuiltTransportClient(e, new Class[0])).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.22.207.12"), 9300));
            esQuery eq = new esQuery(client);
            String queryword = Scan_Print.Scan();
            SearchResponse sr = eq.normalMultiQuery(queryword);
            SearchHit[] Sh = sr.getHits().getHits();
            System.out.println(Sh.length);

            for(int i = 0; i < sr.getHits().getHits().length; ++i) {
                System.out.println(Sh[i].getSource().get("content").toString());
            }

            client.close();
            client.close();
        } catch (Exception var9) {
            var9.printStackTrace();
        }

    }
}


























/*
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;


public class estest {

    //连接elasticsearch
    public static void Link_ES(){
        try {
            //设置集群名称
            Settings settings = Settings.builder().put("client.transport.sniff",true).put("cluster.name", "es").build();
            //创建client
            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.22.207.12"), 9300));

            //搜索数据
            GetResponse response = client.prepareGet("blog", "article", "1").execute().actionGet();
            //输出结果
            System.out.println(response.getSourceAsString());
            //关闭client
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XContentBuilder getMapping(){
        XContentBuilder mapping = null;
        try{

        } catch (Exception e){
            e.printStackTrace();
        }
        return mapping;
    }

    public static void main(String[] args){
        estest test = new estest();
        test.Link_ES();


    }
}
*/


/*
            String json = "{" +
                    "\"id\":1," +
                    "\"title\":\"人生\"," +
                    "\"time\":\"2013-01-30\"," +
                    "\"content\":\"路漫漫其修远兮，吾将上下而求索\"," +
                    "\"url\":\"http://www.baidu.com\"" +
                    "}";
            System.out.println(json);
            IndexResponse response = client.prepareIndex("article", "text")
                    .setSource(json)
                    .get();
*/