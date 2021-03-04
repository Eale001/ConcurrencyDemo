package com.eale.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author Admin
 * @Date 2021/3/4
 * @Description //KafkaConsumerTest
 * @Version 1.0
 **/
public class KafkaConsumerTest implements Runnable{


    private final KafkaConsumer<String,String> consumer;

    private ConsumerRecords<String,String> msgList;

    private final String topic;

    private static final String GROUPID = "GROUPA";

    public KafkaConsumerTest(String topic) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "kafka.test:9092");
        properties.put("group.id",GROUPID);
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(properties);
        this.topic = topic;
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        int messageNo = 1;
        System.out.println("---------开始消费---------");
        try {
            for (;;) {
                msgList = consumer.poll(1000);
                if(null!=msgList&&msgList.count()>0){
                    for (ConsumerRecord<String, String> record : msgList) {
                        //消费100条就打印 ,但打印的数据不一定是这个规律的
                        if(messageNo%100==0){
                            System.out.println(messageNo+"=======receive: key = " + record.key() + ", value = " + record.value()+" offset==="+record.offset());
                        }
                        //当消费了1000条就退出
                        if(messageNo%1000==0){
                            break;
                        }
                        messageNo++;
                    }
                }else{
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }


    public static void main(String[] args) {
        KafkaConsumerTest test = new KafkaConsumerTest("kafka_Test");
        Thread thread = new Thread(test);
        thread.start();

    }
}
