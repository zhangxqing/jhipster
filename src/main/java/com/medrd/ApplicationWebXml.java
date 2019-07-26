package com.medrd;

import com.medrd.config.DefaultProfileUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 这是一个helper Java类，它提供了创建web.xml的替代方法。
 * 只有当应用程序部署到Servlet容器(如Tomcat、JBoss等)时才会调用此函数。
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        /**
         * set a default to use when no profile is configured.
         */
        DefaultProfileUtil.addDefaultProfile(application.application());
        return application.sources(JhipsterApp.class);
    }
}
