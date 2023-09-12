package coupang.mq;

import java.util.UUID;

/**
 * @author gaiqun
 * @date 2023/9/11
 */
public class MyConsumer {

    private String topicName;
    private MyBroker broker;

    public MyConsumer(String topicName, MyBroker broker) {
        this.topicName = topicName;
        this.broker = broker;
        broker.registerConsumer(topicName, this);
    }

    public void consume() {
        String msg = broker.poll(topicName);
        System.out.printf("msg is: %s%n", msg);
    }

    public void receive(String msg) {

    }

    public int hashCode() {
        return UUID.randomUUID().hashCode();
    }

    public boolean equals(MyConsumer consumer) {
        return consumer.hashCode() == hashCode();
    }
}
