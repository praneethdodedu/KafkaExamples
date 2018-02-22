package com.aggregator.news.serdes;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.aggregator.news.model.News;

public class NewsSerdes implements Serde<News> {
	private JsonSerializer<News> serializer = new JsonSerializer<News>();
	private JsonDeserializer<News> deserializer = new JsonDeserializer<News>(News.class);

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		serializer.configure(configs, isKey);
		deserializer.configure(configs, isKey);
	}

	@Override
	public void close() {
		serializer.close();
		deserializer.close();
	}

	@Override
	public Serializer<News> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<News> deserializer() {
		return deserializer;
	}
}