package com.cit.its.shipping.front.observer;

import java.io.IOException;

/**
 * Event 发生接口
 *
 * @author ALion
 * @version 2017/7/13 1:30
 */
public interface EventObserver {

    /**
     * 消息通知
     * @param info 消息
     * @return
     */
    String onEvent(Object info) throws IOException;

}
