package com.scaffold.simple.admin.api.request.project;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: tianjl
 * @Date: 2020/9/24 11:52
 * @Eamil: 2695062879@qq.com
 */
@Data
public class ProjectAddRequest {

    /**
     * 项目id，scaffold的项目id
     */
    @NotNull(message = "projectId can not be null")
    private Integer projectId;

    /**
     * 项目名称
     */
    @NotNull(message = "projectName can not be null")
    private String projectName;

    /**
     * 服务监控检测地址
     */
    private String healthUrl;

    /**
     * java应用堆内存监控地址
     */
    private String dumpUrl;

    /**
     * 项目基本信息
     */
    private String infoUrl;

    /**
     * 项目名称
     */
    private String httpTranceUrl;

    /**
     * 项目配置信息
     */
    private String propertiesUrl;

    /**
     * 其他信息
     */
    private String other;
}
