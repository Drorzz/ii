package org.ayfaar.ii.seo.impl;

import org.ayfaar.ii.seo.SEOView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Drorzz on 06.08.2014.
 */
@Configuration
@PropertySource("classpath:seoThymeleafSpring_test.properties")
public class SEOViewThymeleafSpringConfigurationForTest {

    @Bean
    @Autowired
    public SEOView testView(){
        return new SEOViewThymeleafImplMock("testView");
    }
}
