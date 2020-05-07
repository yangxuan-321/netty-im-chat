package org.chiefdata.config;

import com.startup.contextfilter.ContextFilterParseListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin
 * @Title: ApplicationListenerConf
 * @ProjectName bus_platform
 * @Description: TODO
 * @date 2019/11/19 17:26
 */
@Configuration
public class ApplicationListenerConfig {
    @Bean
    public ContextFilterParseListener appApplicationListener(){
        return new ContextFilterParseListener();
    }
}
