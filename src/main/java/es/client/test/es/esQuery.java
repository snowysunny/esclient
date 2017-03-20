package es.client.test.es;

/**
 * Created by hadoop on 2017/03/20.
 */
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

public class esQuery {
    TransportClient client = null;

    public esQuery(TransportClient TranCli) {
        if(TranCli != null) {
            this.client = TranCli;
        } else {
            System.out.printf("tranclient is error", new Object[0]);
        }

    }

    public SearchResponse marchall() {
        SearchResponse response = (SearchResponse)this.client.prepareSearch(new String[0]).get();
        return response;
    }

    public SearchResponse normalMultiQuery(String query_content) {
        SearchResponse rs = (SearchResponse)this.client.prepareSearch(new String[]{"database"})
                .setTypes(new String[]{"article"})
                .setQuery(QueryBuilders.multiMatchQuery(query_content, new String[]{"content", "title"}))
                .setFrom(0).setSize(10).setExplain(true).get();
        return rs;
    }

    public SearchResponse titleQuery(String query_content) {
        SearchResponse rs = (SearchResponse)this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{"article"}).setQuery(QueryBuilders.matchQuery("title", query_content)).setFrom(0).setSize(10).setExplain(false).get();
        return rs;
    }

    public SearchResponse contentQuery(String query_content) {
        SearchResponse rs = (SearchResponse)this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{"article"}).setQuery(QueryBuilders.termQuery("content", query_content)).setFrom(0).setSize(10).setExplain(false).get();
        return rs;
    }

    public SearchResponse mustFieldQuery(String query_content, String Field) {
        SearchRequestBuilder builder = this.client.prepareSearch(new String[]{"database"}).setTypes(new String[]{"article"}).setSearchType(SearchType.DEFAULT).setFrom(0).setSize(10);
        BoolQueryBuilder qb = QueryBuilders.boolQuery().must((new QueryStringQueryBuilder(query_content)).field(Field));
        builder.setQuery(qb);
        SearchResponse response = (SearchResponse)builder.execute().actionGet();
        return response;
    }
}
