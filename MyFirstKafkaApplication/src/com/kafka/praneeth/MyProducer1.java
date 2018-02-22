package com.kafka.praneeth;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer1 {

	private static Scanner in;

	public static void main(String[] args) throws Exception {
		String topicName = "my-first-topic";
		in = new Scanner(System.in);
		System.out.println("Enter message(type exit to quit) in Producer-1");

		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put("bootstrap.servers", "localhost:9093");
		configProperties.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(configProperties);
		String line = in.nextLine();
		while (!line.equals("exit")) {
			line = "This is from Producer-1 : " + line;
			ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
			producer.send(rec);
			line = in.nextLine();
		}
		in.close();
		producer.close();
	}

}
