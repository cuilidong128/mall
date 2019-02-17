package com.mall.forum.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.mall.forum.core.exceptions.ForumException;
import com.mall.forum.dao.CategoryDao;
import com.mall.forum.modle.Config;
import com.mall.forum.dao.ConfigDao;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JForumConfig extends PropertiesConfiguration{

    private static final Logger logger = Logger.getLogger(JForumConfig.class);

    @Autowired
    public ConfigDao configDao;

    public JForumConfig(ServletContext servletContext) {
        this.setReloadingStrategy(new FileChangedReloadingStrategy());
        this.setDelimiterParsingDisabled(true);
        try {
            loadProps();
            if (servletContext != null) {
                setProperty(ConfigKeys.APPLICATION_PATH, servletContext.getRealPath(""));
            }
            loadDatabaseProperties();
            normalizeTemplateDirectory();
        }
        catch (Exception e) {
            throw new ForumException(e);
        }

    }

    private void loadProps() throws ConfigurationException, Exception {
        this.load(this.getClass().getResourceAsStream("/jforumConfig/SystemGlobals.properties"));
        this.loadCustomProperties();
    }

    private void loadCustomProperties() throws Exception {
        InputStream is = this.getClass().getResourceAsStream("/jforumConfig/jforum-custom.properties");

        if (is != null) {
            Properties custom = new Properties();
            custom.load(is);

            for (Enumeration<?> e = custom.keys(); e.hasMoreElements(); ) {
                String key = (String)e.nextElement();
                this.clearProperty(key);
                this.addProperty(key, custom.get(key));
            }
        }
    }

    private void loadDatabaseProperties() {

        try {
            List<Config> databasesProperties = configDao.selectAll();

            for (Config config : databasesProperties) {
                this.clearProperty(config.getName());
                this.addProperty(config.getName(), config.getValue());
            }
        }
        catch (Exception e) {
            logger.error("Error while trying to load custom settings from the database: " + e.getMessage(), e);
        }

    }

    private void normalizeTemplateDirectory() {
        StringBuilder sb = new StringBuilder(getValue(ConfigKeys.TEMPLATE_DIRECTORY));

        if (sb.charAt(0) != '/') {
            sb.insert(0, '/');
        }

        if (sb.charAt(sb.length() - 1) != '/') {
            sb.append('/');
        }

        setProperty(ConfigKeys.TEMPLATE_DIRECTORY, sb.toString());
    }


    /**
     * @see org.apache.commons.configuration.BaseConfiguration#addPropertyDirect(java.lang.String, java.lang.Object)
     */
    @Override
    protected void addPropertyDirect(String key, Object value) {
        super.addPropertyDirect(key, value);
    }

    /**
     * Gets the complete path to the application root directory
     * @return the path to the root directory
     */
    public String getApplicationPath() {
        return this.getString(ConfigKeys.APPLICATION_PATH);
    }

    /**
     * Delegates to {@link #getString(String)}
     * @param key the key to retrieve
     * @return the key's value
     */
    public String getValue(String key) {
        return this.getString(key);
    }


}

