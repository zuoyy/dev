package com.zuoyy.component.fileUpload.config.properties;

import com.zuoyy.common.utils.ToolUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目-文件上传配置项
 * @author zuo
 */
@Data
@Component
@ConfigurationProperties(prefix = "project.upload")
public class UploadProjectProperties {
    // 上传文件路径
    private String filePath;
    // 上传文件静态访问路径
    private String staticPath = "/upload/**";

    public String getFilePath() {
        if(filePath == null){
            return ToolUtil.getProjectPath() + "/upload/";
        }
        return filePath;
    }
}
