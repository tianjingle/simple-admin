package com.scaffold.simple.admin.application.impl;

import com.scaffold.simple.admin.api.request.project.ProjectAddRequest;
import com.scaffold.simple.admin.application.ProjectManagerService;
import com.scaffold.simple.admin.domain.ProjectDomain;
import com.scaffold.simple.admin.infrestraction.db.ProjectInfoPoMapper;
import com.scaffold.simple.admin.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: tianjl
 * @Date: 2020/9/23 17:57
 * @Eamil: 2695062879@qq.com
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectManagerServiceImpl implements ProjectManagerService {


    /**
     * 项目表
     */
    @Autowired
    private ProjectInfoPoMapper projectInfoPoMapper;

    /**
     * 添加一个项目
     * @param request 添加监控项目
     * @return
     */
    @Override
    public ResponseResult addMonitorProject(ProjectAddRequest request) {
        ProjectDomain projectDomain=new ProjectDomain();
        BeanUtils.copyProperties(request,projectDomain);
        return projectDomain.insert(projectInfoPoMapper);
    }

    /**
     * 删除监控项目
     * @param projectId 项目id
     * @return
     */
    @Override
    public ResponseResult deleteMonitorProject(String projectId) {
        ProjectDomain projectDomain=new ProjectDomain();
        return projectDomain.delete(projectInfoPoMapper,projectId);
    }
}
