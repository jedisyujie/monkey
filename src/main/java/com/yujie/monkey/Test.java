package com.yujie.monkey;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class Test {

	private static Client client = EsClient.getClient();
	
	public static void search(){
		SearchResponse response = client.prepareSearch().execute().actionGet();
		System.out.println(response.toString());
	}
	
	public static void flexSearch(){
		SearchResponse response = client.prepareSearch("community" )
//		        .setTypes("family")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.termQuery("multi","age"))                // Query
		        .setPostFilter(QueryBuilders.rangeQuery("age").from(5).to(9))     // Filter
		        .setFrom(0).setSize(60).setExplain(true)
		        .execute()
		        .actionGet();
		SearchHits hits = response.getHits();
		for (SearchHit searchHit : hits) {
			System.out.println(searchHit.getSourceAsString());
		}
//		System.out.println(response.toString());
	}
	
	public static void main(String[] args) {
		search();
//		flexSearch();
	}
}
