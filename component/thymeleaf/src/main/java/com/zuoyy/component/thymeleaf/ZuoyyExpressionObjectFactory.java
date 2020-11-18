package com.zuoyy.component.thymeleaf;

import com.zuoyy.component.thymeleaf.utility.DictUtil;
import com.zuoyy.component.thymeleaf.utility.LogUtil;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


public class ZuoyyExpressionObjectFactory implements IExpressionObjectFactory {

    public static final String DICT_UTIL_NAME = "dicts";
    public static final DictUtil DICT_UTIL_OBJECT = new DictUtil();
    public static final String LOG_UTIL_NAME = "logs";
    public static final LogUtil LOG_UTIL_OBJECT = new LogUtil();

    @Override
    public Set<String> getAllExpressionObjectNames() {
        Set<String> names = Collections.unmodifiableSet(new LinkedHashSet<String>(Arrays.asList(
                DICT_UTIL_NAME,
                LOG_UTIL_NAME
        )));
        return names;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        if(DICT_UTIL_NAME.equals(expressionObjectName)){
            return DICT_UTIL_OBJECT;
        }
        if(LOG_UTIL_NAME.equals(expressionObjectName)){
            return LOG_UTIL_OBJECT;
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null;
    }
}
