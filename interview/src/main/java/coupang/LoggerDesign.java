package coupang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gaiqun
 * @date 2023/9/6
 */
public class LoggerDesign {

    // 1. logger interface
// 2. 60 min no duplicate log

    interface MyLogger {
        void info(String msg);

        void warn(String msg);

        void error(String msg, Exception ex);

        void setClass(Class clz);
    }

    public enum LogLevel {
        INFO,
        WARN,
        ERROR
    }


    public class MyLoggerImpl1 implements MyLogger {

        private Class clz = null;
        private Thread thread;
        private Map<String, Long> map;


        public MyLoggerImpl1() {
            this.thread = Thread.currentThread();
            this.map = new ConcurrentHashMap<>();
        }

        @Override
        public void setClass(Class clz) {
            this.clz = clz;
        }

        @Override
        public synchronized void info(String msg) {
            if (checkTime(msg)) {
                doPrint(LogLevel.INFO, msg);
            }
        }

        @Override
        public synchronized void warn(String msg) {
            if (checkTime(msg)) {
                doPrint(LogLevel.WARN, msg);
            }
        }

        @Override
        public synchronized void error(String msg, Exception ex) {
            msg = msg + ex.toString();
            if (checkTime(msg)) {
                doPrint(LogLevel.ERROR, msg);
            }
        }

        private void doPrint(LogLevel level, String msg) {
            System.out.printf("%s | %s | %s %s/n", this.thread.getName(), this.clz.getSimpleName(), level.name(), msg);
        }

        private String compress(String msg) {
            //tobe optimized
            return String.valueOf(msg.hashCode());
        }

        private boolean checkTime(String msg) {
            clean();
            String key = compress(msg);
            long currentTime = System.currentTimeMillis();
            if (this.map.containsKey(key)) {
                long lastTime = this.map.get(key);
                if ((currentTime - lastTime) / 1000 < 60 * 60) {
                    return false;
                } else {
                    this.map.put(key, currentTime);
                    return true;
                }
            } else {
                this.map.put(key, currentTime);
                return true;
            }
        }

        private void clean() {
            int size = this.map.size();
            Random rand = new Random();
            int mod = rand.nextInt() % size;
            List<String> keys = new ArrayList<>(this.map.keySet());
            String key = keys.get(mod);
            long currentTime = System.currentTimeMillis();
            long lastTime = this.map.get(key);
            if ((currentTime - lastTime) / 1000 > 60 * 60) {
                this.map.remove(key);
            }
        }
    }

    public static class LoggerFactory {

        //可以通过spring注入，或spi机制
        private static List<MyLogger> loggers = new ArrayList<>();

        public static MyLogger getLogger(Class clz) {
            if (loggers.isEmpty()) {
                throw new RuntimeException("no logger impl found.");
            }
            MyLogger logger = loggers.get(0);
            logger.setClass(clz);
            return logger;
        }
    }


    public static void main(String[] args) {
        MyLogger logger = LoggerFactory.getLogger(LoggerDesign.class);

        logger.info("helo world");
    }


}
