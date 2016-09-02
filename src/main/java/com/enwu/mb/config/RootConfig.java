package com.enwu.mb.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import com.enwu.mb.Application;

@Configuration
@Import({DataSourceConfig.class, JdbcConfig.class})
@ComponentScan(basePackageClasses = Application.class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
//@EnableAspectJAutoProxy
@ImportResource({"classpath:simplesm-context.xml", "classpath:spring/local-context.xml"})
public class RootConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RootConfig.class);

    @Autowired
    private Environment         env;

    /**
     * 打印都是启用的那些Profile配置.方便调试,跟
     *
     */
    @PostConstruct
    public void initApp() {
        LOG.debug("Looking for Spring profiles...");
        if (env.getActiveProfiles().length == 0) {
            LOG.info("No Spring profile configured, running with default configuration.");
        } else {
            for (String profile : env.getActiveProfiles()) {
                LOG.info("Detected Spring profile: {}", profile);
            }
        }
    }
}
