package com.tgram.android.task.controller;

import com.tgram.android.task.bean.BaseResult;
import com.tgram.android.task.bean.Place;
import com.tgram.android.task.bean.Project;
import com.tgram.android.task.bean_request.ProjectDelete;
import com.tgram.android.task.bean_request.ProjectFindByPlace;
import com.tgram.android.task.bean_request.ProjectInsert;
import com.tgram.android.task.bean_request.ProjectUpdate;
import com.tgram.android.task.utils.DateUtil;
import com.tgram.android.task.utils.GsonUtil;
import com.tgram.android.task.utils.StringUtil;
import com.tgram.android.task.utils.SysCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目名称：TaskManager
 * 类描述：项目操作
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
@RestController
@RequestMapping(method = RequestMethod.POST, value = "/api/project")
@Api(value = "项目相关接口", description = "项目相关服务API")
public class ProjectController extends BaseController {

    @ApiOperation("根据地市id，获取关联的项目列表")
    @RequestMapping(method = RequestMethod.POST, value = "findProjectByPlace")
    public String findProjectByPlace(HttpServletRequest request, HttpServletResponse response,
                                     @RequestBody ProjectFindByPlace projectFindByPlace) {
        Long placeId = projectFindByPlace.getPlaceId();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<List<Project>> result = new BaseResult<>();
        List<Project> list = projectMapper.findProjectByPlaceId(placeId + "");
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("请求成功");
        result.setData(list);
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("根据地市id，在地市级别下新增项目")
    @RequestMapping(method = RequestMethod.POST, value = "insert")
    public String insertProject(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody ProjectInsert projectInsert) {
        Long placeId = projectInsert.getPlaceId();
        String name = projectInsert.getName();
        String description = projectInsert.getDescription();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<Project> result = new BaseResult<>();
        Place place = placeMapper.findPlaceById(placeId + "");
        if (null == place) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("未找到地市");
            return GsonUtil.gsonToString(result);
        }
        if (StringUtil.isEmpty(name)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("项目名不能为空");
            return GsonUtil.gsonToString(result);
        }
        Project project = projectMapper.findProjectByPlaceIdAndName(placeId + "", name);
        if (null != project) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该项目名已经存在");
            return GsonUtil.gsonToString(result);
        }
        if (StringUtil.isEmpty(description)) {
            description = "";
        }
        project = new Project();
        project.setPlaceId(placeId);
        project.setProjectName(name);
        project.setDescription(description);
        int row = projectMapper.insertProject(project);
        if (row == 1) {
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("新增项目成功");
            result.setData(project);
        } else {

            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("新增项目失败");
        }
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("根据项目id，修改项目名称和描述信息")
    @RequestMapping(method = RequestMethod.POST, value = "update")
    public String updateProject(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody ProjectUpdate projectUpdate) {
        Long projectId = projectUpdate.getProjectId();
        String name = projectUpdate.getName();
        String description = projectUpdate.getDescription();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<Project> result = new BaseResult<>();
        Project project = projectMapper.findProjectById(projectId + "");
        if (null == project) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("未找到项目");
            return GsonUtil.gsonToString(result);
        }
        if (StringUtil.isEmpty(name)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("项目名不能为空");
            return GsonUtil.gsonToString(result);
        }
        // 查看该地市下是否有重名的项目
        Project otherProject = projectMapper.findProjectByPlaceIdAndName(project.getPlaceId() + "", name);
        if (null != otherProject && otherProject.getPlaceId() != projectId) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该项目名已经存在");
            return GsonUtil.gsonToString(result);
        }
        if (StringUtil.isEmpty(description)) {
            description = "";
        }
        project.setProjectName(name);
        project.setDescription(description);
        int row = projectMapper.updateProject(project);
        if (row == 1) {
            project.setUpdateTime(DateUtil.getDateByFormat(null));
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("修改项目成功");
            result.setData(project);
        } else {

            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("修改项目失败");
        }
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("删除项目，以及关联的记录")
    @RequestMapping(method = RequestMethod.POST, value = "delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody ProjectDelete projectDelete) {
        Long projectId = projectDelete.getProjectId();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<String> result = new BaseResult<>();

        Project project = projectMapper.findProjectById(projectId + "");
        if (null == project) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该项目已经被删除");
            return GsonUtil.gsonToString(result);
        }
        int row = projectMapper.deleteProject(projectId + "");
        logger.info("删除项目数据行数：" + row);
        if (row > 0) {
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("删除项目成功");
        } else {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("删除项目失败");
        }
        return GsonUtil.gsonToString(result);
    }

}
