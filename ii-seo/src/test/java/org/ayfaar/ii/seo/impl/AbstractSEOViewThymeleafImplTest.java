package org.ayfaar.ii.seo.impl;

import org.ayfaar.ii.seo.SEOView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Drorzz on 05.08.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SEOViewThymeleafSpringConfiguration.class,SEOViewThymeleafSpringConfigurationForTest.class})
public class AbstractSEOViewThymeleafImplTest {
    @Autowired
    private SEOView view;

    @Test
    public void testGetHTML() throws Exception {
        Map<String, String> viewParameters = new HashMap<String,String>(){{
            put("param1","value1");
            put("param2","value2");
        }};

        view.setViewParameters(viewParameters);
        String result = view.getHTML();

        assertTrue("Title",result.contains("<title>Test view</title>"));
        assertTrue("Value 1",result.contains("<p>value1</p>"));
        assertTrue("Value 2",result.contains("<p>value2</p>"));
        assertTrue("Value 3",result.contains("<p>value3</p>"));
        assertFalse("Param 1",result.contains("<p>param1</p>"));
        assertFalse("Param 2",result.contains("<p>param2</p>"));
        assertFalse("Param 3",result.contains("<p>param3</p>"));
    }
}