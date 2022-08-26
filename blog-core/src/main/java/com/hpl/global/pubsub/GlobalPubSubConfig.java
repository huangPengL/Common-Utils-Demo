package com.hpl.global.pubsub;

import com.hpl.redis.Publisher;
import com.hpl.redis.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  配置全局频道消息发送器
 * @Author: huangpenglong
 * @Date: 2022/8/23 17:17
 */

@Configuration
public class GlobalPubSubConfig {
    public static final String GLOBAL_CHANNEL = "global_channel";

    private final Publisher globalPublisher;

    @Autowired
    public GlobalPubSubConfig(Redis redis){
        this.globalPublisher = Publisher.of(GLOBAL_CHANNEL, redis);
    }

    @Bean
    public Publisher globalPublisher(){
        return globalPublisher;
    }
}
