package com.kafka.timer;

import com.kafka.kafka;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * com.kafka.timer
 * dxl
 *
 * @author DL
 * @create 2020-07-09
 */
@Component
@EnableScheduling
public class TimeOne {
	@Autowired
//	private KafkaListenerEndpointRegistry registry;
	public static void main(String[] args) {
		Properties prop = new Properties();
//		prop.load(kafka.class.getClassLoader().getResourceAsStream("kafkaConf/producer.properties"));
//		KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(prop);
//		String topic="car_policy";
//
//		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, "zhongkeruan", "112");
//		durableConsumer(record,);


	}
	@Bean("delayContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
																					   ConsumerFactory consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		// 关闭批量消费功能
		factory.setBatchListener(false);
		// 不自动启动
		factory.setAutoStartup(false);
		configurer.configure(factory, consumerFactory);
		return factory;
	}
	@KafkaListener(id="durable",topics = {"car_policy"},groupId = "CarPolicyBatchUpload", containerFactory = "delayContainerFactory")
	public static void durableConsumer(ConsumerRecord<String, String> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer){

		System.err.println("topic值"+record.topic());
		System.err.println("vakue值"+record.value());
		System.err.println("off值"+record.offset());
		System.err.println("partition值"+record.partition());

	}
	// 定时器，每天凌晨0点开启监听
//	@Scheduled(cron = "")
//	public void startListener() {
//		// 判断监听容器是否启动，未启动则将其启动
//		if (!registry.getListenerContainer("durable").isRunning()) {
//			registry.getListenerContainer("durable").start();
//		}
//		registry.getListenerContainer("durable").resume();
//	}
//	// 定时器，每天早上6点关闭监听
//	@Scheduled(cron = "")
//	public void shutDownListener() {
//		registry.getListenerContainer("durable").pause();
//	}

}
