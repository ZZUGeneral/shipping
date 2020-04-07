package com.cit.its.shipping.front;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * //TODO
 *
 * @author YHL
 * @date 2020/3/27 10:58
 */
public class NFDFlightDataTaskListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //销毁时的代码
    }

    @Override
    //在服务启动时，执行此方法。
    public void contextInitialized(ServletContextEvent arg0) {
        new TimerManager();
    }
}

//要执行的任务
class NFDFlightDataTimerTask extends TimerTask {
    @Override
    //此方法为具体要定时操作的方法
    public void run() {
        System.out.println("定时器测试:" + System.currentTimeMillis());
    }
}

class TimerManager {
    private static final long PERIOD_DAY = 6 * 1000;  //每隔六秒执行一次

    public TimerManager() {
        Timer timer = new Timer();     //定时器实例化
        NFDFlightDataTimerTask task = new NFDFlightDataTimerTask();   //要执行的任务
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task, new Date(), PERIOD_DAY);
    }
}
