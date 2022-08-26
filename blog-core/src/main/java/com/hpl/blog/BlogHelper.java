package com.hpl.blog;

import com.hpl.blog.cache.BlogCache;
import com.hpl.blog.entity.Blog;
import com.hpl.global.pubsub.PsMsg;
import com.hpl.global.pubsub.PsmType;
import com.hpl.redis.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: huangpenglong
 * @Date: 2022/8/26 16:48
 */

@Service
public class BlogHelper {

    @Autowired
    private BlogCache blogCache;
    @Autowired
    private Publisher globalPublisher;

    /** 获取缓存 **/
    public List<Blog> get(String blogId){
        return blogCache.get(blogId);
    }

    /** 刷新缓存 **/
    public void flush(){
        globalPublisher.publishJson(PsMsg.of(PsmType.FLUSH_BLOG_CACHE));
    }

}
