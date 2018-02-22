package com.kafka.praneeth;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

public class MyConsumer0 {
	private static Scanner in;

	public static void main(String[] argv) throws Exception {
		in = new Scanner(System.in);
		String topicName = "my-first-topic";
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
		private KafkaConsumer<String, Employee> kafkaConsumer;

		public ConsumerThread(String topicName, String groupId) {
			this.topicName = topicName;
			this.groupId = groupId;
		}

		public void run() {
			Properties configProperties = new Properties();
			configProperties.put("bootstrap.servers", "localhost:9092");
			configProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
			configProperties.put("value.deserializer", "com.kafka.praneeth.EmployeeDeserializer");
			configProperties.put("group.id", groupId);
			configProperties.put("client.id", "simple");

			kafkaConsumer = new KafkaConsumer<String, Employee>(configProperties);
			kafkaConsumer.subscribe(Arrays.asList(topicName));
			try {
				while (true) {
					ConsumerRecords<String, Employee> records = kafkaConsumer.poll(100);
					for (ConsumerRecord<String, Employee> record : records)
						System.out.println("Printing message from Consumer-0 : " + record.value());
				}
			} catch (WakeupException ex) {
				System.out.println("Exception caught " + ex.getMessage());
			} finally {
				kafkaConsumer.close();
				System.out.println("After closing KafkaConsumer");
			}
		}

		public KafkaConsumer<String, Employee> getKafkaConsumer() {
			return this.kafkaConsumer;
		}
	}
}
