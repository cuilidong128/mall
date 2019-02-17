package com.mall.forum.service.impl;

import com.mall.forum.dao.ConfigDao;
import com.mall.forum.modle.Config;
import com.mall.forum.service.CategoryService;
import com.mall.forum.service.ConfigService;
import com.mall.forum.util.ConfigKeys;
import com.mall.forum.util.I18n;
import com.mall.forum.util.JForumConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by cuilidong on 2019/2/8.
 */
@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private  JForumConfig config;

    @Autowired
    private  ConfigDao configDao;

    @Autowired
    private I18n i18n;

    public void save(HttpServletRequest request) {
        for (Enumeration<?> e = request.getParameterNames(); e.hasMoreElements();) {
            String key = (String) e.nextElement();

            if (key.startsWith("p_")) {
                String value = request.getParameter(key);

                String name = key.substring(key.indexOf('_') + 1);
                Config entry = this.configDao.selectByName(name);

                if (entry == null) {
                    entry = new Config();
                    entry.setName(name);
                }

                entry.setValue(value);

                this.config.clearProperty(name);
                this.config.setProperty(name, value);

                this.configDao.updateConfig(entry);
            }
        }

        this.i18n.changeBoardDefaultLanguage(this.config.getValue(ConfigKeys.I18N_DEFAULT));
    }
}
