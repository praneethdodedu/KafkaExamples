package com.aggregator.news.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.aggregator.news.client.model.News;

public class NewsProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsProducer.class);

	@Value("${all.news.topic}")
	private String allNewsTopic;

	@Autowired
	private KafkaTemplate<String, News> kafkaTemplate;

	public void send(News news) {
		LOGGER.info("sending news='{}'", news.toString());
		System.out.println("sending news : " + news.toString());
		kafkaTemplate.send(allNewsTopic, news);
	}
}
