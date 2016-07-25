package com.chenwenning.search.api.utils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.MetricsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgBuilder;

/**
 * Created by chenwenning on 2016/7/25.
 */
public class AggsUtil {

    public static final String AVG_TYPE = "AVG";

    public static final String MAX_TYPE = "MAX";

    public static final String MIN_TYPE = "MIN";

    public static final String COUNT_TYPE ="COUNT";


    /**
     * 组合分组查询
     */
    public static int statisticalQuery(TransportClient client, String index, String type, String field,String stype,String returnType) {
        MetricsAggregationBuilder aBuilder = null;
        if(null != stype){
            if(stype.equals(AggsUtil.AVG_TYPE)){
                aBuilder = AggregationBuilders.avg(returnType).field(field);
            }
            if(stype.equals(AggsUtil.MAX_TYPE)){
                aBuilder = AggregationBuilders.max(returnType).field(field);
            }
            if(stype.equals(AggsUtil.MIN_TYPE)){
                aBuilder = AggregationBuilders.min(returnType).field(field);
            }
            if(stype.equals(AggsUtil.COUNT_TYPE)){
                aBuilder = AggregationBuilders.count(returnType).field(field);
            }
        }
        SearchResponse sr = client.prepareSearch(index).setTypes(type).setSearchType(SearchType.DFS_QUERY_AND_FETCH).addAggregation(aBuilder).setFrom(0).setSize(10).execute().actionGet();
        Terms terms = sr.getAggregations().get(returnType);
        return terms.getBuckets().size();
    }


    public static




}
