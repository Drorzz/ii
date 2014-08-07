package org.ayfaar.ii.seo.impl;

import org.ayfaar.ii.seo.SEOView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Drorzz on 05.08.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SEOViewThymeleafSpringConfiguration.class,AbstractSEOViewThymeleafImplTest.class})
@Configuration
@PropertySource("classpath:seoThymeleafSpring_test.properties")
public class AbstractSEOViewThymeleafImplTest {
    private class SEOViewThymeleafImplMock extends AbstractSEOViewThymeleafImpl {
        public SEOViewThymeleafImplMock(String name){
            super(name);
        }

        @Override
        protected Map<String, ?> getParameters(Map<String, String> viewParameters) {
            Map<String, String> parameters = new HashMap<>(viewParameters);
            parameters.put("param3","value3");
            return parameters;
        }
    }

    @Bean
    public SEOView testView(){
        return new SEOViewThymeleafImplMock("testView");
    }

    @Autowired
    private SEOView testView;

    @Test
    public void testGetHTML() throws Exception {
        Map<String, String> viewParameters = new HashMap<String,String>(){{
            put("param1","value1");
            put("param2","value2");
        }};

        testView.setViewParameters(viewParameters);
        String result = testView.getHTML();

        assertTrue("Title",result.contains("<title>Test view</title>"));
        assertTrue("Value 1",result.contains("<p>value1</p>"));
        assertTrue("Value 2",result.contains("<p>value2</p>"));
        assertTrue("Value 3",result.contains("<p>value3</p>"));
        assertFalse("Param 1",result.contains("<p>param1</p>"));
        assertFalse("Param 2",result.contains("<p>param2</p>"));
        assertFalse("Param 3",result.contains("<p>param3</p>"));
    }
}
