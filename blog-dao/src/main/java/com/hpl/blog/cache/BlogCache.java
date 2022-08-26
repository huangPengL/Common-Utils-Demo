package com.hpl.blog.cache;

import com.hpl.blog.entity.Blog;
import com.hpl.blog.mapper.BlogMapper;
import com.hpl.global.pubsub.PsmDispatcher;
import com.hpl.redis.MessageHandler;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.hpl.global.pubsub.PsmType.FLUSH_BLOG_CACHE;

/**
 * @Author: huangpenglong
 * @Date: 2022/8/26 16:49
 */

@Repository
public class BlogCache implements MessageHandler{

    @Autowired
    private BlogMapper blogMapper;

    public BlogCache() {
        // 作为该消息的订阅者
        PsmDispatcher.addHandler(FLUSH_BLOG_CACHE,this);

        load();
    }

    /* 每隔5分钟加载缓存 */
    public void load(){
        // 加载缓存逻辑...
    }

    /** 订阅者执行该方法 **/
    @Override
    public void handle(String message) {
        load();
    }

    /** 获取缓存内容 **/
    public List<Blog> get(String blogId){
        return Collections.emptyList();
    }

}
