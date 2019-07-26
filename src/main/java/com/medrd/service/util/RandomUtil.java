package com.medrd.service.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 用于生成随机字符串的实用程序类。
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    /**
     * 生成一个密码。
     *
     * @return 生成的密码
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * 生成一个激活密钥。
     *
     * @return 生成的激活密钥
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * 生成重置key。
     *
     * @return 生成的重置key
     */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
}
