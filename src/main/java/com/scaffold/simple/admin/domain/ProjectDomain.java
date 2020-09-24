package com.scaffold.simple.admin.domain;

import com.scaffold.simple.admin.infrestraction.db.ProjectInfoPoMapper;
import com.scaffold.simple.admin.infrestraction.db.po.ProjectInfoPo;
import com.scaffold.simple.admin.utils.ResponseResult;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @Author: tianjl
 * @Date: 2020/9/23 18:16
 * @Eamil: 2695062879@qq.com
 */
@Data
public class ProjectDomain {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 项目id，scaffold的项目id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 其他信息
     */
    private String other;

    /**
     * 添加到数据库
     * @param projectInfoPoMapper
     * @return
     */
    public ResponseResult insert(ProjectInfoPoMapper projectInfoPoMapper) {
        ProjectInfoPo po=new ProjectInfoPo();
        BeanUtils.copyProperties(this,po);
        po.setCreateTime(new Date());
        if (projectInfoPoMapper.insert(po)>0){
            return ResponseResult.success(true);
        }
        return ResponseResult.error("add fail");
    }

    /**
     * 删除项目监控
     *
     * @param projectInfoPoMapper
     * @param projectId 删除项目
     * @return 删除项目
     */
    public ResponseResult delete(ProjectInfoPoMapper projectInfoPoMapper, String projectId) {
        Example example=new Example(ProjectInfoPo.class);
        example.createCriteria().andEqualTo("projectId",projectId);
        if (projectInfoPoMapper.deleteByExample(projectId)>0){
            return ResponseResult.success(true);
        }
        return ResponseResult.error("delete fail");
    }
}
