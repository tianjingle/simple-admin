package com.scaffold.simple.admin.application;

import com.scaffold.simple.admin.api.request.project.ProjectAddRequest;
import com.scaffold.simple.admin.utils.ResponseResult;

/**
 * @Author: tianjl
 * @Date: 2020/9/23 17:57
 * @Eamil: 2695062879@qq.com
 */
public interface ProjectManagerService {

    /**
     * 添加一个监控项目
     * @param request 添加监控项目
     * @return 结果
     */
    ResponseResult addMonitorProject(ProjectAddRequest request);

    /**
     * 删除监控项目
     * @param projectId 项目id
     * @return 结果
     */
    ResponseResult deleteMonitorProject(String projectId);
}
