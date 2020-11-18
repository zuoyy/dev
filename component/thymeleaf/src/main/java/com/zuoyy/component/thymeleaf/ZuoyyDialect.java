package com.zuoyy.component.thymeleaf;

import com.zuoyy.component.thymeleaf.attribute.SelectDictAttrProcessor;
import com.zuoyy.component.thymeleaf.attribute.SelectListAttrProcessor;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.LinkedHashSet;
import java.util.Set;


public class ZuoyyDialect extends AbstractProcessorDialect implements IExpressionObjectDialect {

    private static final String NAME = "ZuoyyDialect";
    private static final String PREFIX = "zy";
    private IExpressionObjectFactory expressionObjectFactory = null;

    public ZuoyyDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        LinkedHashSet processors = new LinkedHashSet();
        processors.add(new SelectDictAttrProcessor(TemplateMode.HTML, dialectPrefix));
        processors.add(new SelectListAttrProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        if (this.expressionObjectFactory == null) {
            this.expressionObjectFactory = new ZuoyyExpressionObjectFactory();
        }
        return this.expressionObjectFactory;
    }
}
