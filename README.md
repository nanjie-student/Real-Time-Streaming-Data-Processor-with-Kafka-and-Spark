# Real-Time-Streaming-Data-Processor-with-Kafka-and-Spark
Summary:
This project demonstrates a real-time streaming data processing pipeline using Apache Kafka and Apache Spark.
The goal of the project is to simulate a system where transaction data is produced in real-time, streamed through
Kafka, and processed using Spark Streaming to calculate metrics and detect anomalies. It provides an end-to-end solution, 
including:

Environment Setup:

Dockerized infrastructure for Kafka (with Zookeeper) and Spark, ensuring a scalable and isolated development environment.
Producer Implementation:

A Kafka producer implemented in Java that generates simulated transaction data and sends it to a Kafka topic (transactions).
Consumer and Streaming Processor:

A Spark Streaming application written in Java that consumes real-time transaction data from Kafka, processes it, and performs tasks such as printing the data, calculating metrics (e.g., total transaction amount), and identifying anomalies.
Real-Time Metrics and Insights:

Processes transaction data in 10-second windows.
Calculates total transaction amounts.
Detects transactions exceeding a specific threshold as potential anomalies.
This project serves as a foundation for understanding real-time data streaming pipelines and can be extended to integrate with databases, dashboards, or machine learning models for advanced analytics.

