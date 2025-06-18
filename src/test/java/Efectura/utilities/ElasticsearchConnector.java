package Efectura.utilities;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.CountRequest;
import co.elastic.clients.elasticsearch.core.CountResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.json.JsonData;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;



public class ElasticsearchConnector {
    public static ElasticsearchClient createClient() {
        // 🔒 Kullanıcı adı ve şifreyi buraya yaz
        String username = "elasticuser";
        String password = "t4aBPoPAs58G0IdYtNo6Mku5"; // → şifreyi buraya

        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.129.6", 9200, "http")
        ).setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder.setDefaultCredentialsProvider(credsProvider)
        ).build();

        RestClientTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper()
        );

        return new ElasticsearchClient(transport);
    }

    public static long getLogCount(ElasticsearchClient client, String index, String serviceName) throws Exception {
        Query query = QueryBuilders.bool()
                .must(QueryBuilders.matchPhrase(m -> m.field("properties.ComponentName").query("diaservice")))
                .must(QueryBuilders.matchPhrase(m -> m.field("level").query("ERROR")))
                .must(QueryBuilders.range(r -> r.field("@timestamp").gte(JsonData.of("now-24h"))))
                .build()
                ._toQuery();

        CountRequest request = CountRequest.of(b -> b
                .index("tr-fletum-cdp-diaservice")
                .query(query)
        );

        CountResponse response = client.count(request);
        System.out.println("diaservice Error log count (last 48h): " + response.count());


        return response.count();
    }

    public static long getLogCount(
            ElasticsearchClient client,
            String index,
            String componentName,
            String componentFieldPath,
            String logLevel,
            String logLevelFieldPath,
            String timestampFieldPath,
            String sinceDuration // e.g. "now-48h", "now-7d"
    ) throws Exception {

        Query query = QueryBuilders.bool()
                .must(QueryBuilders.matchPhrase(m -> m
                        .field(componentFieldPath)
                        .query(componentName)))
                .must(QueryBuilders.matchPhrase(m -> m
                        .field(logLevelFieldPath)
                        .query(logLevel)))
                .must(QueryBuilders.range(r -> r
                        .field(timestampFieldPath)
                        .gte(JsonData.of(sinceDuration))))
                .build()
                ._toQuery();

        CountRequest request = CountRequest.of(b -> b
                .index(index)
                .query(query)
        );

        CountResponse response = client.count(request);
        return response.count();
    }


}
