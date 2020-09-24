package com.scaffold.simple.admin.api;

import com.scaffold.simple.admin.api.request.project.ProjectAddRequest;
import com.scaffold.simple.admin.application.ProjectManagerService;
import com.scaffold.simple.admin.utils.ResponseResult;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: tianjl
 * @Date: 2020/9/23 17:56
 * @Eamil: 2695062879@qq.com
 */
@RestController
@RequestMapping(value = "/project")
public class ProjectApi {


    @Autowired
    private ProjectManagerService projectManagerService;

    /***
     * 添加一个监控项目
     * @param request 添加监控项目
     * @return 结构
     */
    @PostMapping(value = "/add")
    public ResponseResult projectAdd(@Valid @RequestBody ProjectAddRequest request){
        return projectManagerService.addMonitorProject(request);
    }


    /**
     * 删除监控项目
     * @param projectId 项目id
     * @return 结果
     */
    @GetMapping(value = "/delete/{projectId}")
    public ResponseResult delete(@PathVariable String projectId){
        return projectManagerService.deleteMonitorProject(projectId);
    }
}
