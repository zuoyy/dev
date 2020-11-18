package com.zuoyy.devtools.generate.template;

import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.common.utils.ToolUtil;
import com.zuoyy.devtools.generate.domain.Generate;
import com.zuoyy.devtools.generate.enums.FieldType;
import com.zuoyy.devtools.generate.enums.TierType;
import com.zuoyy.devtools.generate.utils.GenerateUtil;
import com.zuoyy.devtools.generate.utils.jAngel.nodes.*;
import com.zuoyy.devtools.generate.utils.parser.JavaParseUtil;
import com.zuoyy.modules.common.DataEntity;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

/**
 * @author zuo
 */
public class EntityTemplate {

    /**
     * 生成类体
     */
    private static Document genClazzBody(Generate generate){
        // 获取生成信息
        String prefix = generate.getBasic().getTablePrefix();
        String tableName = generate.getBasic().getTableName();

        // 获取jAngel文档对象
        Document document = JavaParseUtil.document(generate, TierType.DOMAIN);
        ClassNode clazz = document.getClazz();

        // 实现接口注解 继承 DataEntity
        clazz.setExtends(DataEntity.class);
        clazz.addImplements(Serializable.class);
        clazz.addAnnotation(Data.class);
        clazz.addAnnotation(Entity.class);
        clazz.addAnnotation(Table.class, Format.of("name=$S", prefix + tableName));
        clazz.addAnnotation(EntityListeners.class, Format.of("$T.class", AuditingEntityListener.class));
        clazz.addAnnotation(Where.class, Format.of("clause = $T.notDelete", StatusUtil.class));

        // 生成类字段
        generate.getFields().forEach(field -> {
            // 创建字段节点
            String name = field.getName();
            FieldNode node = new FieldNode(name);

            // 获取字段类型
            Map<Long, String> fieldType = ToolUtil.enumToMap(FieldType.class);
            String type = fieldType.get((long) field.getType());

            // 判断如果类型为Text则进行注解处理
            if (type.equals(FieldType.Text.getMessage())){
                node.addAnnotation(Lob.class);
                node.addAnnotation(Column.class, Format.of("columnDefinition=$S", "TEXT"));
                node.setType(String.class);
            }

            // 将字段节点附加到实体类上
            if (node.getType() == null){
                node.setType(type);
                // 格式化时间类型
                if (type.equals(FieldType.Date.getMessage())){
                    node.addAnnotation(DateTimeFormat.class, Format.of("pattern=$S", "yyyy-MM-dd HH:mm:ss"));
                }
                // 处理高精度浮点型BigDecimal
                if (type.equals(FieldType.BigDecimal.getMessage())){
                    document.getContainer().importClass(BigDecimal.class);
                }
            }

            // 排除主键ID注释
            if (!name.equals("name")){
                node.setComments("// " + field.getTitle());
            }

            node.accessSym(Modifier.PRIVATE);
            clazz.append(node);
        });

        return document;
    }

    /**
     * 生成实体类模板
     */
    public static String generate(Generate generate) {
        // 生成文件
        String filePath = GenerateUtil.getJavaFilePath(generate, TierType.DOMAIN);
        try {
            Document document = genClazzBody(generate);
            GenerateUtil.generateFile(filePath, document.content());
        } catch (FileAlreadyExistsException e) {
            return GenerateUtil.fileExist(filePath);
        }
        return filePath;
    }
}
