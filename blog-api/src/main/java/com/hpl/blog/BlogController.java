package com.hpl.blog;

import com.hpl.global.SimpleReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: huangpenglong
 * @Date: 2022/8/26 16:43
 */

@RestController
public class BlogController {

    @Autowired
    private BlogHelper blogHelper;

    /** 刷新缓存 **/
    @PostMapping("/blog/flush")
    public SimpleReply blogFlush(){
        blogHelper.flush();
        return SimpleReply.empty();
    }

}
