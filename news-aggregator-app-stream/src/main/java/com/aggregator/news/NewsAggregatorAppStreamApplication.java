package com.aggregator.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.aggregator.news.stream.NewsAggregatorStream;

@SpringBootApplication
public class NewsAggregatorAppStreamApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NewsAggregatorAppStreamApplication.class, args);
		context.getBean(NewsAggregatorStream.class).streamToTopic();
	}
}
