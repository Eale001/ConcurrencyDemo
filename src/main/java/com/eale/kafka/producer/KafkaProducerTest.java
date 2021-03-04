package com.eale.kafka.producer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author Admin
 * @Date 2021/3/4
 * @Description // KafkaProducerTest
 * @Version 1.0
 **/
public class KafkaProducerTest implements Runnable{

    private final KafkaProducer<String,String> producer;

    private final String topic;

    public KafkaProducerTest(String topic) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka.test:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        this.producer = new KafkaProducer<String, String>(properties);
        this.topic = topic;
    }

    public static void main(String[] args) {
        KafkaProducerTest producerTest = new KafkaProducerTest("kafka_Test");
        Thread thread = new Thread(producerTest);
        thread.start();
    }


    @Override
    public void run() {
        int messageNo = 1;
        try {
            for(;;) {
                String messageStr="你好，这是第"+messageNo+"条数据";
                producer.send(new ProducerRecord<String, String>(topic, "Message", messageStr));
                //生产了100条就打印
                if(messageNo%100==0){
                    System.out.println("发送的信息:" + messageStr);
                }
                //生产1000条就退出
                if(messageNo%1000==0){
                    System.out.println("成功发送了"+messageNo+"条");
                    break;
                }
                messageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
