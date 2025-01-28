package com.spark.dataProcessor.spark;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SparkStreamingConsumer {
    public static void main(String[] args) throws InterruptedException {
        // 初始化 Spark 配置
        SparkConf conf = new SparkConf().setAppName("KafkaSparkStreaming").setMaster("local[*]");
        JavaStreamingContext streamingContext = new JavaStreamingContext(conf, Durations.seconds(10));

        // 配置 Kafka
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        kafkaParams.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaParams.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaParams.put(ConsumerConfig.GROUP_ID_CONFIG, "spark-consumer-group");
        kafkaParams.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // 订阅 Kafka 主题
        Collection<String> topics = Arrays.asList("transactions");

        // 从 Kafka 创建数据流
        JavaInputDStream<org.apache.kafka.clients.consumer.ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        streamingContext,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.Subscribe(topics, kafkaParams)
                );

        // 处理数据：打印收到的消息
        stream.map(record -> record.value())
                .foreachRDD(rdd -> {
                    if (!rdd.isEmpty()) {
                        rdd.foreach(data -> System.out.println("Received: " + data));
                    }
                });

        // 启动流处理
        streamingContext.start();
        streamingContext.awaitTermination();
    }
}
