package coupang.mq;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gaiqun
 * @date 2023/9/11
 */
public class MyBroker {

    private static final int MAX_QUEUE_SIZE = 1000;

    private static Map<String, Topic> topicMap = new ConcurrentHashMap<>();
    private static Map<String, Set<MyConsumer>> consumerSetMap = new ConcurrentHashMap<>();

    public static class Topic {
        private MsgType type;
        private ArrayBlockingQueue<String> queue;

        public Topic(MsgType type, ArrayBlockingQueue<String> queue) {
            this.type = type;
            this.queue = queue;
        }

        public ArrayBlockingQueue<String> getQueue() {
            return queue;
        }

        public MsgType getType() {
            return type;
        }
    }

    private static MyBroker instance = null;

    private MyBroker() {

    }

    public static MyBroker getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (MyBroker.class) {
            instance = new MyBroker();
            return instance;
        }
    }

    public void register(String topicName, MsgType msgType) {
        check(topicName);
        if (topicMap.containsKey(topicName)) {
            return;
        }
        synchronized (this) {
            if (topicMap.containsKey(topicName)) {
                return;
            }
            Topic topic = new Topic(msgType, new ArrayBlockingQueue<>(MAX_QUEUE_SIZE));
            topicMap.put(topicName, topic);
        }
    }

    public void registerConsumer(String topicName, MyConsumer consumer) {
        if (topicMap.containsKey(topicName)) {
            return;
        }
        synchronized (this) {
            Set<MyConsumer> consumers = consumerSetMap.getOrDefault(topicName, new HashSet<>());
            consumers.add(consumer);
            consumerSetMap.put(topicName, consumers);
        }
    }

    public void push(String topicName, String msg) {
        if (!topicMap.containsKey(topicName)) {
            throw new RuntimeException("topic not registered");
        }
        Topic topic = topicMap.get(topicName);
        topic.getQueue().offer(msg);
    }

    private static void check(String topicName) {
        if (topicName == null || topicName.trim().isEmpty()) {
            throw new RuntimeException("invalid topic name");
        }
    }

    public String poll(String topicName) {
        if (!topicMap.containsKey(topicName)) {
            throw new RuntimeException("topic not registered");
        }
        Topic topic = topicMap.get(topicName);
        String msg = "";
        switch (topic.getType()) {
            case PULL:
                msg = doPull(topicName, topic.getQueue());
                break;
            case PUSH:
                msg = doPush(topicName, topic.getQueue());
                break;
            default:
                throw new RuntimeException("");
        }
        return msg;
    }

    public static String doPull(String topicName, ArrayBlockingQueue<String> queue) {
        return queue.poll();
    }

    public static String doPush(String topicName, ArrayBlockingQueue<String> queue) {
        int size = consumerSetMap.size();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(size, size, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(size));
        while (!queue.isEmpty()) {
            executor.submit(() -> {
                Set<MyConsumer> consumers = consumerSetMap.get(topicName);
                for (MyConsumer consumer : consumers) {
                    consumer.receive(queue.poll());
                }
            });
        }
        return "";
    }
}
