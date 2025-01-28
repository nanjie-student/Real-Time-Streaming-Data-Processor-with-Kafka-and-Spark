package com.spark.dataProcessor.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class kafkaProduceor {
    public static void main(String[] args) {
        // 配置 Kafka 生产者属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka Broker 地址
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 模拟发送数据
        for (int i = 1; i <= 10; i++) {
            String transaction = "{\"transactionId\":\"" + i + "\", \"amount\": " + (100 + i * 10) + "}";
            producer.send(new ProducerRecord<>("transactions", null, transaction));
            System.out.println("Sent: " + transaction);
        }

        producer.close();
    }
}
