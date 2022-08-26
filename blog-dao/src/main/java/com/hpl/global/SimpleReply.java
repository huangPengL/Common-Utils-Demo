package com.hpl.global;

import lombok.Data;

import java.util.Map;

/**
 * @Author: huangpenglong
 * @Date: 2022/8/26 16:46
 */

@Data
public class SimpleReply {

    private int code;
    private String message;
    private Map<String, Object> data;

    private static final SimpleReply EMPTY = new SimpleReply(200, "", null);

    public SimpleReply(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static SimpleReply empty() {
        return EMPTY;
    }
}
