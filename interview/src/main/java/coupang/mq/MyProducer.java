package coupang.mq;

/**
 * @author gaiqun
 * @date 2023/9/11
 */
public class MyProducer {

    private String topicName;
    private MyBroker broker;

    public MyProducer(String topicName, MyBroker broker, MsgType msgType) {
        this.topicName = topicName;
        this.broker = broker;
        broker.register(topicName, msgType);
    }

    public void send(String msg) {
        broker.push(topicName, msg);
    }
}
