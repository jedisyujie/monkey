package com.yujie.monkey;

import java.io.IOException;

import javax.annotation.PostConstruct;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;

public class JestClientTest {
	
	private static JestClient client;

	
	static void init() {

		 JestClientFactory factory = new JestClientFactory();
		 factory.setHttpClientConfig(new HttpClientConfig
		                        .Builder("http://localhost:9200")
		                        .multiThreaded(true)
		                        .build());
		 client = factory.getObject();
		 System.out.println(client);
	}
	
	public static void main(String[] args) {
		init();
		Delete delete = new Delete.Builder("").index("community").type("family").build();
	//	Get get = new Get.Builder("zoo", "AVMuBNqN4R8LmPN_Aq-f").type("monkeys").build();
		DocumentResult result2 = null;
		try {
			 result2 = client.execute(delete);
//		    result = client.execute(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result2.getJsonString());
	}
}
