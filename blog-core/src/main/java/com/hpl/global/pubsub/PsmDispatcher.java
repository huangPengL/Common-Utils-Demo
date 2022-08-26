package com.hpl.global.pubsub;

import com.alibaba.fastjson.JSON;
import com.hpl.redis.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;

/**
 * 全局消息分发器，收到频道的消息后根据消息的内容分发给其他消息处理器处理
 * 当项目初始化的时候注册在Subscriber中
 *
 * @Author: huangpenglong
 * @Date: 2022/8/23 16:56
 */
public class PsmDispatcher implements MessageHandler{

    private static final Logger log = LoggerFactory.getLogger(PsmDispatcher.class);

    private static final PsmDispatcher instance = new PsmDispatcher();

    private static final EnumMap<PsmType, MessageHandler> handlers = new EnumMap<>(PsmType.class);

    // 单例
    public static final PsmDispatcher getInstance(){
        return instance;
    }

    /* 注册消息处理器 */
    public static void addHandler(PsmType type, MessageHandler handler){
        handlers.put(type, handler);
    }

    private PsmDispatcher(){}

    @Override
    public void handle(String message) {
        PsMsg msg = null;
        try {
            msg = JSON.parseObject(message, PsMsg.class);
        }catch (Exception e){
            log.error(new StringBuilder(64).append("ex=").append(e.getClass().getSimpleName()).append(", errmsg=")
                    .append(e.getMessage()).toString());
        }

        if(msg == null){
            return;
        }

        MessageHandler messageHandler = handlers.get(msg.getType());
        if(messageHandler == null){
            return;
        }

        try{
            messageHandler.handle(msg.getData());
        } catch (Exception e) {
            log.error("handle psmsg error: {}", new StringBuilder(64).append("ex=")
                            .append(e.getClass().getSimpleName()).append(", errmsg=").append(e.getMessage()));
        }
    }
}
