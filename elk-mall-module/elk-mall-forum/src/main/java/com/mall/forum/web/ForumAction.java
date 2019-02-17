package com.mall.forum.web;

import com.mall.common.util.ResponseMessage;
import com.mall.forum.modle.Forum;
import com.mall.forum.modle.ForumWatch;
import com.mall.forum.modle.Group;
import com.mall.forum.modle.User;
import com.mall.forum.service.ForumService;
import com.mall.forum.service.GroupService;
import com.mall.forum.util.FastjsonUtil;
import com.mall.forum.util.RedisUtil;
import com.mall.forum.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.mall.common.util.ResponseMessage.ok;
/**
 * Created by cuilidong on 2019/2/3.
 */
@RestController
@RequestMapping("/forum")
public class ForumAction {
    @Autowired
    private ForumService forumService;

    @Autowired
    RedisUtil redisUtil;



    /**
     * 论坛列表
     */
    @PostMapping("/list")
    public ResponseMessage<List<Forum>> list(){
        List list =null;

        list = forumService.selectAll();

//        String str = redisUtil.get("forums");
//        if(!StringHelper.isBlank(str)){
//            list =FastjsonUtil.from(str,List.class);
//        }else{
//            list = forumService.selectAll();
//            if(list.size()>0){
//                redisUtil.set("forums", FastjsonUtil.to(list));
//            }
//        }
        return ok(list);

    }

    /**
     * 返回论坛观看人数
     * @return
     */

    @PostMapping("/watch")
    public List<ForumWatch> watch(){

        final String userKey ="USER_LIST:";
        final String forumWacthKey ="FORUM_WACTH:";
        User user = new User();
        user.setId(1);
        user.setFirstName("支付宝");
        user.setEmail("zhifubao@163.com");

        //redisUtil.lRightPush(userKey,FastjsonUtil.to(user));
        user = new User();
        user.setId(2);
        user.setFirstName("微信");
        user.setEmail("weixin@163.com");
        //redisUtil.lRightPush(userKey,FastjsonUtil.to(user));
        user = new User();
        user.setId(3);
        user.setFirstName("抖音");
        user.setEmail("douyin@163.com");
        //redisUtil.lRightPush(userKey,FastjsonUtil.to(user));
        user = new User();
        user.setId(4);
        user.setFirstName("头条");
        user.setEmail("toutiao@163.com");
        //redisUtil.lRightPush(userKey,FastjsonUtil.to(user));
        user = new User();
        user.setId(5);
        user.setFirstName("微博");
        user.setEmail("weibo@163.com");
        //redisUtil.lRightPush(userKey,FastjsonUtil.to(user));
        user = new User();
        user.setId(6);
        user.setFirstName("网易");
        user.setEmail("wangyi@163.com");
        //redisUtil.lRightPush(userKey,FastjsonUtil.to(user));
//        List list = redisUtil.lRange(userKey,0,6);
//        Long lLen = redisUtil.lLen(userKey);

        //redisUtil.delete(userKey);

        //List<ForumWatch> list = new ArrayList<>();

        ForumWatch watch = new ForumWatch();
        watch.setForumId(1);
        watch.setId(1);
        //redisUtil.lRightPush(forumWacthKey,FastjsonUtil.to(watch));

        watch = new ForumWatch();
        watch.setForumId(1);
        watch.setId(2);
        //redisUtil.lRightPush(forumWacthKey,FastjsonUtil.to(watch));

        watch = new ForumWatch();
        watch.setForumId(1);
        watch.setId(3);
        //redisUtil.lRightPush(forumWacthKey,FastjsonUtil.to(watch));

        redisUtil.lRemove(forumWacthKey,0,FastjsonUtil.to(watch));

        List list = redisUtil.lRange(forumWacthKey,0,6);
        Long lLen = redisUtil.lLen(forumWacthKey);



        return list;
    }
}
