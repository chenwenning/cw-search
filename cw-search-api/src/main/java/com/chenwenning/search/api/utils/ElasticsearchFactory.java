package com.chenwenning.search.api.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;

/**
 * Created by chenwenning on 2016/7/9.
 * elasticsearch 工厂类
 */
public class ElasticsearchFactory {

    private static TransportClient transportClient = null;


    public static TransportClient getInstance() {
        if (transportClient == null) {
            transportClient = initES();
        }
        return transportClient;
    }


    public static TransportClient initES() {
        if (transportClient == null) {
            Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").build();
            /*
            * .put("cluster.name", "elasticsearch")
			.put("discovery.type", "zen")//发现集群方式

			.put("discovery.zen.minimum_master_nodes", 2)//最少有2个master存在

			.put("discovery.zen.ping_timeout", "200ms")//集群ping时间，太小可能会因为网络通信而导致不能发现集群

			.put("discovery.initial_state_timeout", "500ms")
			.put("gateway.type", "local")//(fs, none, local)

			.put("index.number_of_shards", 1)
			.put("action.auto_create_index", false)//配置是否自动创建索引

			.put("cluster.routing.schedule", "50ms")//发现新节点时间
            * */

            transportClient = TransportClient.builder().settings(settings).build();
            String transportAddresses = ESPropertyUtil.getProperties("transportAddresses", "");
            if (!"".equals(transportAddresses)) {
                String[] transports = transportAddresses.split(",");
                for (String transport : transports) {
                    String[] addressAndPort = transport.split(":");
                    InetSocketTransportAddress inetSocketTransportAddress = null;
                    try {
                        inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(addressAndPort[0]), Integer.valueOf(addressAndPort[1]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    transportClient.addTransportAddresses(inetSocketTransportAddress);
                }
            }
            return transportClient;
        }
        return transportClient;
    }

    public static void closeES() {
        if (transportClient != null) {
            transportClient.close();
        }
    }

}
