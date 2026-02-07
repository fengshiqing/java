/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.client;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.DeleteByQueryResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.indices.Alias;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fengshiqing.springcloud.service.elasticsearch.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 功能描述：ElasticSearch客户端
 *
 * @author 冯仕清
 * @since 2024-07-21
 */
@Slf4j
@Component
public class EsClient {

    private static ElasticsearchClient elasticsearchClient;

    /**
     * 功能描述：初始化客户端
     */
    @PostConstruct
    private void initClient() {
        String hostname = "192.168.0.100";

        // 基本的用户名密码认证
        String username = "elastic";
        String password = "h2ZOdakL7D7g*5faoO46";
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(hostname, 9200, "http"));
        restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder ->
                httpAsyncClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider));
        RestClient restClient = restClientBuilder.build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        elasticsearchClient = new ElasticsearchClient(transport);
    }

    /**
     * 功能描述：创建索引
     * ElasticSearch-java 客户端可以使用连续调用的方式进行编程。
     */
    public void createIndex() throws IOException {
        CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(
                new CreateIndexRequest.Builder()
                        .index("product_info")
                        .aliases("product_info_alias", new Alias.Builder().isWriteIndex(true).build())
                        .build()
        );
        if (createIndexResponse.acknowledged()) {
            log.info("创建索引成功！");
        } else {
            log.error("创建索引失败！");
        }
    }

    /**
     * 功能描述：添加数据。
     * ElasticSearch-java 客户端是以 document 的方式添加数据到 ES，这个 document 就是一个 Object 对象，
     * 就是把我们创建的实体直接通过写入 Index 中，这样使用起来更加方便。
     *
     * @throws IOException 异常
     */
    public void addData() throws IOException {
        ProductInfo productInfo = new ProductInfo("红米手机", "红米 Note，智能手机，价格便宜", "蓝色", 999);
        CreateResponse createResponse = elasticsearchClient.create(createRequest -> createRequest.id("1003").index("product_info").document(productInfo));
        System.out.println("添加索引请求结果：" + createResponse.result());
    }

    /**
     * 功能描述：删除数据。
     * 以下是比较简单的删除操作，直接通过 ID 进行删除
     *
     * @throws IOException 异常
     */
    public void delDataVidId(String id) throws IOException {
        DeleteResponse deleteResponse = elasticsearchClient.delete(deleteReqest -> deleteReqest.index("product_info").id(id));
        System.out.println("按照 ID 删除索引数据结果：" + deleteResponse.result());
    }


    /**
     * 功能描述：删除数据。
     * 但有时候需要按照某个条件删除，该如何做呢？代码如下：
     * 注意：下面示例是按照条件删除的，比如参数 value 是 “苹果手机”，因为通过 productName 检索会把 “苹果” 和 “手机” 的都检索出来，那样的话就都删除了。
     *
     * @param value 入参
     * @throws IOException 异常
     */
    public void delDataVidQuery(String value) throws IOException {
        DeleteByQueryResponse deleteByQueryResponse = elasticsearchClient.deleteByQuery(deleteByQuery ->
                deleteByQuery.index("product_info").query(query ->
                        query.match(MatchQuery.of(builder -> builder.field("productName").query(value)))));
        System.out.println("按照条件删除索引数据结果：" + deleteByQueryResponse.deleted());
    }

    /**
     * 那我们只想删除 “苹果手机” 的数据该如何做呢？其实很简单，以 keyword【productName.keyword】的形式查询就行，代码如下：
     *
     * @param value 入参
     * @throws IOException 异常
     */
    public void delDataVidQuery1(String value) throws IOException {
        DeleteByQueryResponse deleteByQueryResponse = elasticsearchClient.deleteByQuery(deleteByQuery ->
                deleteByQuery.index("product_info").query(query ->
                        query.match(MatchQuery.of(builder -> builder.queryName("productName.keyword").query(value)))));
        System.out.println("按照条件删除索引数据结果：" + deleteByQueryResponse.deleted());
    }

    /**
     * 功能描述：修改数据。
     * 下面的示例是通过 ID 修改数据，这种都比较简单：
     *
     * @param id 入参
     * @throws IOException 异常
     */
    public void updateViaId(String id) throws IOException {
        ProductInfo productInfo = new ProductInfo("苹果手机", "苹果智能手机，价格实惠便宜", "黑色", 5999);
        UpdateResponse<ProductInfo> updateResponse = elasticsearchClient.update(updateRequest ->
                updateRequest.index("product_info").id(id).doc(productInfo), ProductInfo.class);
        System.out.println("根据 ID 修改：" + updateResponse.result());
    }


    /**
     * 功能描述：查询索引
     *
     * @param value 入参
     * @throws IOException 异常
     */
    public void query(String value) throws IOException {
        SearchResponse<ProductInfo> searchResponse = elasticsearchClient.search(searchRequest ->
                searchRequest.index("product_info").query(q -> q.match(MatchQuery.of(builder ->
                        builder.field("productName").query(value)))), ProductInfo.class);
        searchResponse.hits().hits().forEach(System.out::println);
    }


}
