package com.medrd.config;

import io.github.jhipster.config.JHipsterConstants;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;

import java.util.*;

/**
 * 类来加载要用作默认值的Spring配置文件
 * 当没有<code>spring.profiles时。活动</code>设置在环境中或作为命令行参数。
 * 如果该值在<code>应用程序中不可用。yml</code>则<code>dev</code> profile将被用作缺省值。
 */
public final class DefaultProfileUtil {

    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil() {
    }

    /**
     * 设置一个默认值，当没有配置配置文件时使用。
     *
     * @param app the Spring application
     */
    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        /*
        * 没有定义其他配置文件时使用的默认配置文件
        * 这不能在<code>应用程序中设置。yml > < /代码文件。
        * 参见https://github.com/spring-projects/spring-boot/issues/1219
        */
        defProperties.put(SPRING_PROFILE_DEFAULT, JHipsterConstants.SPRING_PROFILE_DEVELOPMENT);
        app.setDefaultProperties(defProperties);
    }

    /**
     * 获取应用程序的概要文件，否则获取默认概要文件。
     *
     * @param env spring environment
     * @return profiles
     */
    public static String[] getActiveProfiles(Environment env) {
        String[] profiles = env.getActiveProfiles();
        if (profiles.length == 0) {
            return env.getDefaultProfiles();
        }
        return profiles;
    }
}
