package com.aggregator.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.aggregator.news.client.NewsAggregator;

@SpringBootApplication
public class NewsAggregatorAppProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NewsAggregatorAppProducerApplication.class, args);
		context.getBean(NewsAggregator.class).sendNewsToTopic();
	}

}
