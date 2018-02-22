package com.aggregator.news.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.aggregator.news.model.News;

@Component
public class NewsCategoryProducer {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	public void produce(String topicName, News news) {

		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		Producer<String, News> producer = new KafkaProducer<String, News>(configProperties);
		ProducerRecord<String, News> rec = new ProducerRecord<String, News>(topicName, null, news);
		producer.send(rec, new Callback() {
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				System.out.println(
						"Message sent to topic ->" + metadata.topic() + " stored at offset->" + metadata.offset());
			}
		});
		producer.close();

	}

}
