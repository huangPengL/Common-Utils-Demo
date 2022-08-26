package com.hpl.global.pubsub;

import com.alibaba.fastjson.JSON;

/**
 * @Author: huangpenglong
 * @Date: 2022/8/23 17:08
 */
public class PsMsg {
    // 消息类型
    private final PsmType type;

    // 业务数据
    private final String data;

    public static final PsMsg of(PsmType type){
        return new PsMsg(type, null);
    }

    public static final PsMsg ofJson(PsmType type, Object obj){
        return new PsMsg(type, JSON.toJSONString(obj));
    }


    public PsMsg(PsmType type, String data) {
        this.type = type;
        this.data = data;
    }

    public PsmType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
