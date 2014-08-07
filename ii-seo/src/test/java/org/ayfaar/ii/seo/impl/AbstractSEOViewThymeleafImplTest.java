package org.ayfaar.ii.seo.impl;

import org.ayfaar.ii.seo.SEOView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Drorzz on 05.08.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SEOViewThymeleafSpringConfiguration.class,AbstractSEOViewThymeleafImplTest.class})
@Configuration
@PropertySource("classpath:seoThymeleafSpring_test.properties")
public class AbstractSEOViewThymeleafImplTest {
    @Bean
    public SEOView testView(){
        AbstractSEOViewThymeleafImpl view = mock(AbstractSEOViewThymeleafImpl.class);
        ReflectionTestUtils.setField(view,"name","testView");
        when(view.getHTML()).thenCallRealMethod();
        when(view.getParameters(anyMap())).then(new Answer<Map<String, String>>() {
            @Override
            public Map<String, String> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new HashMap<String,String>(){{
                    put("param1", "value1");
                    put("param2", "value2");
                    put("param3", "value3");
                }};
            }});
        return view;
    }

    @Autowired
    private SEOView testView;

    @Test
    public void testGetHTML() throws Exception {
        testView.setViewParameters(anyMap());
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
