package com.cit.its.shipping.front.observer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息 订阅/分发 具体实现类
 * 1.单例设计模式（静态内部类）
 * 2.观察者设计模式
 */

public class Notify implements MyNotifier {

    /**
     * 观察者集合
     * 注: key的String类型可以根据个人喜好修改为Integer
     */
    private static Map<String, HashSet<EventObserver>> mObservers;

    private static class Inner {
        private static final MyNotifier INSTANCE = new Notify();
    }

    public static MyNotifier getNotifier() {
        return Inner.INSTANCE;
    }

    private Notify() {
        mObservers = new ConcurrentHashMap<>();
    }

    @Override
    public void subscribe(String topic, EventObserver observer) {
        checkKey(topic);
        checkObserver(observer);

        HashSet<EventObserver> set = mObservers.get(topic);
        if (set == null) {
            set = new HashSet<>();
        }
        set.add(observer);
        mObservers.put(topic, set);
    }

    @Override
    public void unSubscribe(EventObserver observer) {
        checkObserver(observer);

        for (Map.Entry<String, HashSet<EventObserver>> entry : mObservers.entrySet()) {
            HashSet<EventObserver> set = entry.getValue();
            if (set.remove(observer)) {
                break;
            }
        }
    }

    @Override
    public void unSubscribe(String topic) {
        checkKey(topic);

        mObservers.remove(topic);
    }

    @Override
    public void unRegisterAll() {
        mObservers.clear();
    }

    @Override
    public String post(String topic, Object info) throws IOException {
        checkKey(topic);
        String jsonContent = "";
        HashSet<EventObserver> observers = mObservers.get(topic);
        if (observers != null) {
            for (EventObserver observer : observers) {
                jsonContent = observer.onEvent(info);
                return jsonContent;
            }
        }
        return jsonContent;
    }

    @Override
    public void postAll(Object info) throws IOException {
        for (Map.Entry<String, HashSet<EventObserver>> entry : mObservers.entrySet()) {
            for (EventObserver observer : entry.getValue()) {
                observer.onEvent(info);
            }
        }
    }

    private void checkObserver(EventObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("observer should not be null!");
        }
    }

    private void checkKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key should not be null!");
        }
    }
}
