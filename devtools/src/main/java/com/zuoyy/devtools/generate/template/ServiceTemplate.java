package com.zuoyy.devtools.generate.template;

import com.zuoyy.common.utils.ToolUtil;
import com.zuoyy.devtools.generate.domain.Generate;
import com.zuoyy.devtools.generate.enums.TierType;
import com.zuoyy.devtools.generate.utils.FileUtil;
import com.zuoyy.devtools.generate.utils.GenerateUtil;
import com.zuoyy.devtools.generate.utils.jAngel.JAngelContainer;
import com.zuoyy.devtools.generate.utils.jAngel.nodes.Document;
import com.zuoyy.devtools.generate.utils.jAngel.parser.Expression;
import com.zuoyy.devtools.generate.utils.parser.JavaParseUtil;
import com.zuoyy.modules.common.BaseService;
import org.springframework.data.domain.Page;

import java.nio.file.FileAlreadyExistsException;
import java.util.Set;

/**
 * @author zuo
 */
public class ServiceTemplate {

    /**
     * 生成需要导入的包
     */
    private static Set<String> genImports(Generate generate) {
        JAngelContainer container = new JAngelContainer();
        container.importClass(JavaParseUtil.getPackage(generate, TierType.DOMAIN));
        container.importClass(JavaParseUtil.getPackage(generate, TierType.QUERY));
        container.importClass(BaseService.class);
        container.importClass(Page.class);
        return container.getImports();
    }

    /**
     * 生成类体
     */
    private static Document genClazzBody(Generate generate) {
        // 构建数据-模板表达式
        Expression expression = new Expression();
        expression.label("name", ToolUtil.lowerFirst(generate.getBasic().getTableEntity()));
        expression.label("entity", generate.getBasic().getTableEntity());
        String path = FileUtil.templatePath(ServiceTemplate.class);

        // 获取jAngel文档对象
        Document document = JavaParseUtil.document(path, expression, generate, TierType.SERVICE);
        document.getContainer().importClass(genImports(generate));

        return document;
    }

    /**
     * 生成服务层模板
     */
    public static String generate(Generate generate) {
        // 生成文件
        String filePath = GenerateUtil.getJavaFilePath(generate, TierType.SERVICE);
        try {
            Document document = genClazzBody(generate);
            GenerateUtil.generateFile(filePath, document.content());
        } catch (FileAlreadyExistsException e) {
            return GenerateUtil.fileExist(filePath);
        }
        return filePath;
    }
}
