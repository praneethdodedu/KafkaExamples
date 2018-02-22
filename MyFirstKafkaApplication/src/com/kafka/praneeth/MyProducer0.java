package com.kafka.praneeth;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer0 {

	private static Scanner in;

	public static void main(String[] args) throws Exception {
		String topicName = "my-first-topic";
		in = new Scanner(System.in);
		System.out.println("Enter message(type exit to quit) in Producer-0");

		// Configure the Producer
		Properties configProperties = new Properties();
		configProperties.put("bootstrap.servers", "localhost:9092");
		configProperties.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put("value.serializer", "com.kafka.praneeth.EmployeeSerializer");

		Producer<String, Employee> producer = new KafkaProducer<String, Employee>(configProperties);
		String line = in.nextLine();
		while (!line.equals("exit")) {
			line = "This is from Producer-0 : " + line;
			Employee employee = new Employee(line, "java", "pes");
			ProducerRecord<String, Employee> rec = new ProducerRecord<String, Employee>(topicName, employee);
			producer.send(rec);
			line = in.nextLine();
		}
		in.close();
		producer.close();
	}

}
