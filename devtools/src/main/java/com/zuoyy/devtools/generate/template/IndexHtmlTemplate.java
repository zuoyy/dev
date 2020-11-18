package com.zuoyy.devtools.generate.template;

import com.zuoyy.common.utils.ToolUtil;
import com.zuoyy.devtools.generate.domain.Generate;
import com.zuoyy.devtools.generate.enums.TierType;
import com.zuoyy.devtools.generate.utils.FileUtil;
import com.zuoyy.devtools.generate.utils.GenerateUtil;
import com.zuoyy.devtools.generate.utils.parser.HtmlParseUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

/**
 * @author zuo
 */
public class IndexHtmlTemplate {

    /**
     * 生成页面
     */
    private static String genHtmlBody(Generate generate) throws IOException {
        // 构建数据
        String path = FileUtil.templatePath(IndexHtmlTemplate.class);

        // 获取Jsoup文档对象
        Document document = HtmlParseUtil.document(path);
        // 替换基本数据
        String html = HtmlParseUtil.html(document);
        html = html.replace("#{name}",  ToolUtil.lowerFirst(generate.getBasic().getTableEntity()));
        return html;
    }

    /**
     * 生成列表页面模板
     */
    public static String generate(Generate generate) {
        String filePath = GenerateUtil.getHtmlFilePath(generate, TierType.INDEX);
        try {
            String content = IndexHtmlTemplate.genHtmlBody(generate);
            GenerateUtil.generateFile(filePath, content);
        } catch (FileAlreadyExistsException e) {
            return GenerateUtil.fileExist(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
