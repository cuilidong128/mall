package com.mall.utils;

import java.util.Date;

import com.mall.common.util.constant.Constant;
import com.mall.common.util.exception.RRException;
import com.mall.common.util.redis.RedisKeys;
import com.mall.common.util.redis.RedisUtils;
import com.mall.common.util.security.SecurityUtil;
import com.mall.common.util.spring.SpringContextUtils;
import com.mall.system.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;


public class ShiroUtils {

    /**  加密算法 */
    public final static String hashAlgorithmName = "SHA-256";
    /**  循环次数 */
    public final static int hashIterations = 16;

    public static String sha256(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

    public static Session getSession() {
        RedisUtils redisUtils=SpringContextUtils.getBean(RedisUtils.class);
        //	  String key = RedisKeys.getShiroSessionKey(configKey);
        //     redisUtils.get(key, SysConfig.class);
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getUserEntity() {
        SysUser sysUser =(SysUser)SecurityUtils.getSubject().getPrincipal();
        //安全考虑忽略敏感信息
        if(sysUser!=null){
            sysUser.setSalt(null);
            sysUser.setPassword(null);
        }
        return sysUser;
    }

    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
        RedisUtils redisUtils=SpringContextUtils.getBean(RedisUtils.class);
        String kapthaKey = RedisKeys.getKaptchaKey(getSession().getId().toString());
        redisUtils.set(kapthaKey, value, Constant.HALF_HOUR);
        String sessionId =HttpContextUtils.getHttpServletRequest().getSession().getId();
        String rawKey = RedisKeys.getReqId(SecurityUtil.encryptSHA(sessionId+ value));
        redisUtils.set(rawKey, value,Constant.HALF_HOUR);
    }

    public static Object getSessionAttribute(Object key) {
        RedisUtils redisUtils=SpringContextUtils.getBean(RedisUtils.class);
        String kaptchaKey = RedisKeys.getKaptchaKey(getSession().getId().toString());
        return getSession().getAttribute(key) != null ? getSession().getAttribute(key):redisUtils.get(kaptchaKey);
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        String sessionId = getSession().getId().toString();
        RedisUtils redisUtils=SpringContextUtils.getBean(RedisUtils.class);
        String sessionKey = RedisKeys.getShiroSessionKey(sessionId);
        String kaptchaKey = RedisKeys.getKaptchaKey(sessionId);
        redisUtils.delete(sessionKey);
        redisUtils.delete(kaptchaKey);
        SecurityUtils.getSubject().logout();
    }

    public void userLogout(String sessionId){
        org.apache.shiro.mgt.SecurityManager securityManager = SecurityUtils.getSecurityManager();
        Subject.Builder builder = new Subject.Builder(securityManager);
        builder.sessionId(sessionId);
        Subject subject = builder.buildSubject();
        if (null != subject) {
            try {
                subject.logout();
            } catch (SessionException e) {
                // TODO: handle exception
            }
        }
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if(kaptcha == null){
            throw new RRException("验证码已失效");
        }
        getSession().removeAttribute(key);
        RedisUtils redisUtils= SpringContextUtils.getBean(RedisUtils.class);
        String kaptchaKey = RedisKeys.getKaptchaKey(key);
        redisUtils.delete(kaptchaKey);
        return kaptcha.toString();
    }


}
