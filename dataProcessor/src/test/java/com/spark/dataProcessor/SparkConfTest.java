package com.spark.dataProcessor;


public class SparkConfTest {
    public static <SparkConf> void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("TestSparkConf")
                .setMaster("local[*]");

        System.out.println("SparkConf initialized successfully!");
    }
}
