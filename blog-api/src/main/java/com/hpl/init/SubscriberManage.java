package com.hpl.init;

import com.hpl.global.pubsub.GlobalPubSubConfig;
import com.hpl.global.pubsub.PsmDispatcher;
import com.hpl.redis.Redis;
import com.hpl.redis.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;

/**
 *  在项目初始化的时候，启动一系列订阅器
 * @Author: huangpenglong
 * @Date: 2022/8/23 17:33
 */

@Component
public class SubscriberManage {
    private final Subscriber subscriber;

    @Autowired
    public SubscriberManage(Redis redis, @Qualifier("threadPool") ExecutorService threadPool){
        subscriber = new Subscriber(redis, threadPool);

        // 全局消息订阅
        subscriber.addMessageHandler(GlobalPubSubConfig.GLOBAL_CHANNEL, PsmDispatcher.getInstance());

        // 启动订阅
        subscriber.start();
    }

    @PreDestroy
    public void preDestroy(){
        subscriber.unSubscribe();
        subscriber.interrupt();
    }

}
