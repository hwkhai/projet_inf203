package com.csdnes.utils;


import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class EsUtils {

    private volatile static TransportClient client;
    public static final String CLUSTER_NAME = "elasticsearch_weiliu";
    public static final String HOST_IP = "localhost";
    public static final int TCP_PORT = 9300;

    static Settings settings =
            Settings.builder()
                    .put("cluster.name", CLUSTER_NAME)
                    .build();

    @SuppressWarnings({ "resource", "unchecked" })
	public static TransportClient getClient() {
        try {
            client = new PreBuiltTransportClient(settings).addTransportAddress(
                            new TransportAddress(InetAddress.getByName(HOST_IP), TCP_PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }


    @SuppressWarnings({ "resource", "unchecked" })
	public static TransportClient getSingleClient() {
        if (client == null) {
            synchronized (TransportClient.class) {
            }
            if (client == null) {
                try {
                    client = new PreBuiltTransportClient(settings)
                            .addTransportAddress(new TransportAddress(
                                    InetAddress.getByName(HOST_IP), TCP_PORT));
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("getSingleClient():" + client);
        return client;
    }


    public static IndicesAdminClient getIndicesAdminClient() {

        return getSingleClient().admin().indices();
    }


    public static boolean createIndex(String indexName) {


        CreateIndexResponse creatIndexResponse = getIndicesAdminClient()
                .prepareCreate(indexName.toLowerCase())
                .setSettings(Settings.builder()
                        .put("index.number_of_shards", 3)
                        .put("index.number_of_replicas", 2))
                .execute().actionGet();

        return creatIndexResponse.isAcknowledged();
    }


    public static boolean setIndexMapping(String indexName, String typeName, String mappingStr) {
        PutMappingResponse resp = getIndicesAdminClient().preparePutMapping(indexName)
                .setType(typeName)
                .setSource(mappingStr)
                .get();

        return resp.isAcknowledged();
    }


    public static boolean deleteIndex(String indexName) {

        DeleteIndexResponse deleteIndexResponse = getIndicesAdminClient()
                .prepareDelete(indexName.toLowerCase())
                .execute()
                .actionGet();

        return deleteIndexResponse.isAcknowledged();
    }


    public static void main(String[] args) {
        TransportClient client1 = getClient();
        TransportClient client2 = getClient();
        System.out.println(client1);
        System.out.println(client2);
        System.out.println(client1 == client2);


        TransportClient singleClient1 = getSingleClient();
        TransportClient singleClient2 = getSingleClient();
        System.out.println(singleClient1);
        System.out.println(singleClient2);
        System.out.println(singleClient1 == singleClient2);


        System.out.println("*******************************");
        IndicesAdminClient adminClient1 = getIndicesAdminClient();
        IndicesAdminClient adminClient2 = getIndicesAdminClient();

        System.out.println(adminClient1);
        System.out.println(adminClient2);
//
        @SuppressWarnings("unused")
		GetResponse resp = client1
                .prepareGet("inf203", "article_source", null)
                .get();
//        System.out.println(resp.getSourceAsString());

        GetResponse resp2 = client1
                .prepareGet("inf203", "article_source", null)
                .execute().actionGet();

        System.out.println(resp2.getSourceAsString());
    }

}
