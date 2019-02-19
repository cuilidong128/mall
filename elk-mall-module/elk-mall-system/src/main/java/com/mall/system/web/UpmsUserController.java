package com.mall.system.web;


import com.alibaba.dubbo.common.json.JSONObject;
import com.mall.common.base.controller.BaseController;
import com.mall.common.exception.BusinessException;
import com.mall.system.cache.Cache;
import com.mall.system.cache.RedisTemplateCache;
import com.mall.system.model.UpmsUser;
import com.mall.system.service.UpmsUserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mall.system.cache.CacheFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/manage/user")
public class UpmsUserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;


    //private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplateCache cache;

    @Autowired
    private CacheFactory factory;

    private static final Cache M_CACHE = CacheFactory.getCache("MCache");


    //@ApiOperation(value = "用户首页")
    //@RequiresPermissions("upms:user:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index() {
        try {
            long startTime=System.currentTimeMillis();
            JSONObject object =  (JSONObject)M_CACHE.get("name");
            if(object ==null){
                UpmsUser user = upmsUserService.selectByPrimaryKey(1);
                JSONObject obj = new JSONObject();
                obj.put("user", user);
                M_CACHE.put("name",obj);
            }else{
                UpmsUser upmsUser = (UpmsUser) object.get("user");
                System.out.println("name redisUser ---> "+ upmsUser.getRealname());
            }
            System.out.println(" memory ---> ");

           //UpmsUser redisUser =  (UpmsUser)redisTemplate.opsForValue().get("k_user_object");
//            cache.cacheValue("name",user);
//            UpmsUser redisUser = (UpmsUser)cache.getValue("name");
           //String name = cache.getValue("name");


            //
//            if(redisUser == null){
//
//                redisTemplate.opsForValue().set("k_user_object",user);
//                System.out.println("name 1 ---> "+redisUser.getRealname());
//            }else{
//                System.out.println("name redisUser ---> "+redisUser.getRealname());
//                //redisTemplate.delete("k_user_object");
//            }
            long endTime=System.currentTimeMillis();
            LOGGER.info("程序运行时间： "+(endTime - startTime)+"ms");
        }catch (BusinessException exception){
            System.out.println("exception ---> "+exception.toString());
            exception.printStackTrace();

        }







       //RedisUtil.set("name","cuilidong" ,10);
        //redisCache.cacheSet("name",user.getRealname());

        //redisCache.getValue("name");


    }

    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public void index1() {
        List<Integer> integers = Arrays.asList(2, 4, 6, 8);
        integers.forEach((x)->System.out.println("-----"+x));
        integers.forEach(x->System.out.println("x"+x));
        integers.forEach(x->{
            x = x*18;
            System.out.println("x"+x);
        });
        long startTime=System.currentTimeMillis();
        UpmsUser user = upmsUserService.selectByPrimaryKey(1);
        long endTime=System.currentTimeMillis();
        LOGGER.info("程序运行时间： "+(endTime - startTime)+"ms");
    }

}
