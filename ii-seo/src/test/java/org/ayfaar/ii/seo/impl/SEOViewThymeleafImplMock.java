package org.ayfaar.ii.seo.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Drorzz on 06.08.2014.
 */
public class SEOViewThymeleafImplMock extends AbstractSEOViewThymeleafImpl {
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
