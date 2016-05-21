package com.yujie.monkey;

import java.net.InetAddress;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class EsClient {

	public static Client getClient(){
		Client client = null;
		try {
			Settings settings = Settings.settingsBuilder()
			        .put("cluster.name", "monkey").build();
			client = TransportClient.builder().settings(settings).build()
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (Exception e) {
		}
		return client;
	}
	
	public static void main(String[] args) {
		
		Client client = getClient();
		for (int i = 1; i < 1100; i++) {
			String json = "{" +
					"\"name\":\"test"+i+"\"," +
					"\"age\":"+i+"," +
					 "\"postDate\":\"2016-01-30\"," +
					"\"message\":\"just test\"" +
					"}";
			
			IndexResponse response = client.prepareIndex("community", "family",i+"")
					.setSource(json)
					.get();
			System.out.println(response.toString());
		}
		client.close();
	}
}
