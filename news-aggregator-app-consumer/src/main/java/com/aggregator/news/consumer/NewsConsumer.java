package com.aggregator.news.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.aggregator.news.model.News;

@Component
public class NewsConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsConsumer.class);

	@KafkaListener(topics = "${technology.news.topic}")
	public void receiveFromTechnology(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Technology :" + news.toString());
	}

	@KafkaListener(topics = "${business.news.topic}")
	public void receiveFromBusiness(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Business  :" + news.toString());
	}

	@KafkaListener(topics = "${entertainment.news.topic}")
	public void receiveFromEntertainment(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Entertainment :" + news.toString());
	}

	@KafkaListener(topics = "${healthcare.news.topic}")
	public void receiveFromHealthcare(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Healthcare :" + news.toString());
	}

	@KafkaListener(topics = "${sports.news.topic}")
	public void receiveFromSports(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Sports :" + news.toString());
	}

	@KafkaListener(topics = "${political.news.topic}")
	public void receiveFromPolitical(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Political :" + news.toString());
	}

	@KafkaListener(topics = "${india.news.topic}")
	public void receiveFromIndia(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("India :" + news.toString());
	}

	@KafkaListener(topics = "${world.news.topic}")
	public void receiveFromWorld(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("World :" + news.toString());
	}

	@KafkaListener(topics = "${other.news.topic}")
	public void receiveFromOther(News news) {
		LOGGER.info("received news='{}'", news.toString());
		System.out.println("Other :" + news.toString());
	}
}
