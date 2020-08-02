package com.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * com
 * dxl
 *
 * @author DL
 * @create 2020-07-05
 */
public class kafka {
	public static void main(String[] args) throws Exception{
		Properties prop = new Properties();
		prop.load(kafka.class.getClassLoader().getResourceAsStream("kafkaConf/producer.properties"));
		KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(prop);
		String topic="car_policy";

		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, "zhongkeruan", "112");


		// 该方法是个异步方法，会先放到缓冲区，再消费下一条；等缓存区满了或时间到了会送kafka
		Future<RecordMetadata> future = kafkaProducer.send(record);
		Future<RecordMetadata> future1 = kafkaProducer.send(record);
		RecordMetadata recordMetadata = future.get();
		RecordMetadata recordMetadata1 = future1.get();
		long offset = recordMetadata.offset();
		int partition = recordMetadata.partition();
		String topic1 = recordMetadata.topic();
		long offset1 = recordMetadata1.offset();
		int partition1 = recordMetadata1.partition();
		String topic2 = recordMetadata1.topic();
		System.err.println("打印"+topic1+" === "+partition+"====="+offset);
		System.err.println("打印"+topic2+" === "+partition1+"====="+offset1);


		kafkaProducer.close();


	}


}

