package com.mall.forum.web;

import com.mall.common.util.ResponseMessage;
import com.mall.forum.service.ConfigService;
import com.mall.forum.service.ForumService;
import com.mall.forum.util.JForumConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.mall.common.util.ResponseMessage.ok;
/**
 * Created by cuilidong on 2019/2/8.
 */

@RestController
@RequestMapping("/config")
public class ConfigAction {

    @Autowired
    private ConfigService configService;
    @Autowired
    private JForumConfig config;


    @PostMapping("/list")
    public ResponseMessage<Map<String, Object>> list() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("locales", this.loadLocaleNames());
        map.put("config", this.config);
        return ok(map);
    }

    @PostMapping("/save")
    public ResponseMessage<Map<String, Object>> save(HttpServletRequest request) throws Exception {
        this.configService.save(request);
        HashMap<String, Object> map = new HashMap<>();
        map.put("locales", this.loadLocaleNames());
        map.put("config", this.config);
        return ok(map);
    }

    private List<String> loadLocaleNames() throws Exception {
        Properties locales = new Properties();

        locales.load(this.getClass().getResourceAsStream("/jforumConfig/languages/locales.properties"));
        List<String> localesList = new ArrayList<String>();

        for (Enumeration<?> e = locales.keys(); e.hasMoreElements();) {
            localesList.add((String) e.nextElement());
        }

        return localesList;
    }
}
