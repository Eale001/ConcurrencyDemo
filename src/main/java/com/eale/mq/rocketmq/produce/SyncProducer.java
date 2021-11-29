package com.eale.mq.rocketmq.produce;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 *
 * Producer端发送同步消息
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 * @Author HLD
 * @Date 2021/8/30 0030
 **/
public class SyncProducer {


    public static void main(String[] args) throws Exception {

        // 实例化消息生产者 producer
        DefaultMQProducer producer = new DefaultMQProducer("unique_group_name");
        // 设置nameServer地址
        producer.setNamesrvAddr("192.168.0.189:9876");
        // 启动producer实力
        producer.start();
        for (int i = 0; i <100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message message = new Message("TopicTest",
                    "TestTag",
                    /* Message body */
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息到一个Broker
            SendResult send = producer.send(message);
            // 通过sendResult返回消息是否成功送达
            System.out.println("send result " + send);
        }

        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }


}
