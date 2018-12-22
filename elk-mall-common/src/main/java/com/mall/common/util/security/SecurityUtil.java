package com.mall.common.util.security;


import com.mall.common.util.security.coder.BASE64Encoder;
import com.mall.common.util.security.coder.SHACoder;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * 默认算法密钥
     */
    private static final byte[] ENCRYPT_KEY = { -81, 0, 105, 7, -32, 26, -49, 88 };

    public static final String CHARSET = "UTF-8";


    public static final String encryptBASE64(byte[] key) {
        try {
            return new BASE64Encoder().encode(key);
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }


    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static final String encryptSHA(String data) {
        try {
            return encryptBASE64(SHACoder.encodeSHA256(data.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new RuntimeException("加密错误，错误信息：", e);
        }
    }


}
