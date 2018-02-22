package com.kafka.praneeth;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

public class MyConsumer1 {
	private static Scanner in;

	public static void main(String[] argv) throws Exception {
		in = new Scanner(System.in);
		String topicName = "first-test-topic";
		String groupId = "group1";

		ConsumerThread consumerRunnable = new ConsumerThread(topicName, groupId);
		consumerRunnable.start();
		String line = "";
		while (!line.equals("exit")) {
			line = in.next();
		}
		consumerRunnable.getKafkaConsumer().wakeup();
		System.out.println("Stopping consumer .....");
		consumerRunnable.join();
	}

	private static class ConsumerThread extends Thread {
		private String topicName;
		private String groupId;
		private KafkaConsumer<String, String> kafkaConsumer;

		public ConsumerThread(String topicName, String groupId) {
			this.topicName = topicName;
			this.groupId = groupId;
		}

		public void run() {
			Properties configProperties = new Properties();
			configProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
			configProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			configProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			configProperties.put("group.id", groupId);
			configProperties.put("client.id", "simple");

			// Figure out where to start processing messages from
			kafkaConsumer = new KafkaConsumer<String, String>(configProperties);
			kafkaConsumer.subscribe(Arrays.asList(topicName));
			// Start processing messages
			try {
				while (true) {
					ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
					for (ConsumerRecord<String, String> record : records)
						System.out.println("Printing message from Consumer-1 : " + record.value());
				}
			} catch (WakeupException ex) {
				System.out.println("Exception caught " + ex.getMessage());
			} finally {
				kafkaConsumer.close();
				System.out.println("After closing KafkaConsumer");
			}
		}

		public KafkaConsumer<String, String> getKafkaConsumer() {
			return this.kafkaConsumer;
		}
	}
}