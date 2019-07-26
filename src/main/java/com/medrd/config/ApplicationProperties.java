package com.medrd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 自定义配置文件类
 * <p>
 * 属性在应用程序中配置.yml文件。
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

}
