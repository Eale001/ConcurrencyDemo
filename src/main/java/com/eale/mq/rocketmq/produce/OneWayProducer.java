package com.eale.mq.rocketmq.produce;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 单向发送消息
 * 这种方式主要用在不特别关心发送结果的场景，例如日志发送。
 *
 * @Author HLD
 * @Date 2021/8/30 0030
 **/
public class OneWayProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("one_way_group_name");
        producer.setNamesrvAddr("192.168.0.189:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("TestTopic", "TestTag",
                    /* Message body */
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(message);
        }
        producer.shutdown();
    }

}
