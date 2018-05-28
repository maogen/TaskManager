package com.tgram.android.task.mapper;

import com.tgram.android.task.bean.Project;

import java.util.List;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public interface ProjectMapper {

    /**
     * 根据地市id查询关联的项目
     *
     * @param placeId
     * @return
     */
    List<Project> findProjectByPlaceId(String placeId);

    /**
     * 根据id查找项目
     *
     * @param projectId
     * @return
     */
    Project findProjectById(String projectId);

    /**
     * 根据地市id和项目名查找项目
     *
     * @param placeId
     * @param projectName
     * @return
     */
    Project findProjectByPlaceIdAndName(String placeId, String projectName);

    /**
     * 新增项目
     *
     * @param project
     * @return
     */
    int insertProject(Project project);

    /**
     * 更新项目信息
     *
     * @param project
     * @return
     */
    int updateProject(Project project);

    /**
     * 删除项目和项目关联的记录
     * @param projectId
     * @return
     */
    int deleteProject(String projectId);

}
