package com.tgram.android.task.controller;

import com.tgram.android.task.bean.BaseResult;
import com.tgram.android.task.bean.IPRecord;
import com.tgram.android.task.bean.Project;
import com.tgram.android.task.bean_request.RecordDelete;
import com.tgram.android.task.bean_request.RecordFindByProject;
import com.tgram.android.task.bean_request.RecordInsert;
import com.tgram.android.task.bean_request.RecordUpdate;
import com.tgram.android.task.utils.DateUtil;
import com.tgram.android.task.utils.GsonUtil;
import com.tgram.android.task.utils.SysCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/23
 */
@RestController
@RequestMapping(method = RequestMethod.POST, value = "/api/ipRecord")
@Api(value = "记录相关接口", description = "ip、端口记录相关服务API")
public class IPRecordController extends BaseController {

    @ApiOperation("根据项目id查询关联的记录")
    @RequestMapping(method = RequestMethod.POST, value = "findRecordByProject")
    public String findRecordByProject(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody RecordFindByProject recordFindByProject) {
        Long projectId = recordFindByProject.getProjectId();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<List<IPRecord>> result = new BaseResult<>();
        List<IPRecord> list = ipRecordMapper.findRecordByProject(projectId + "");
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("查询成功");
        result.setData(list);
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("新增记录")
    @RequestMapping(method = RequestMethod.POST, value = "insert")
    public String insertRecord(HttpServletRequest request, HttpServletResponse response,
                               @ApiParam(value = "projectId（项目id）;\nrecordName（自定义记录名）;\n" +
                                       "mainIP（服务器ip地址，例如：192.168.0.37）;\n mainPort（服务器端口，例如：8080）;\n" +
                                       "mainMappingIP（映射后的ip地址，例如：10.10.0.1）;\n mainMappingPort（映射后的端口，例如：17001）;\n" +
                                       "mapUrl（地图url，例如：http://192.168.20.90:17002/map/china/zhongshan/zhongshanpgissltoandroid.xml）;\n" +
                                       "description（记录描述）")
                               @RequestBody RecordInsert recordInsert) {
        Long projectId = recordInsert.getProjectId();
        String recordName = recordInsert.getRecordName();
        String mainIP = recordInsert.getMainIP();
        String mainPort = recordInsert.getMainPort();
        String mainMappingIP = recordInsert.getMainMappingIP();
        String mainMappingPort = recordInsert.getMainMappingPort();
        String mapUrl = recordInsert.getMapUrl();
        String description = recordInsert.getDescription();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<IPRecord> result = new BaseResult<>();
        Project project = projectMapper.findProjectById(projectId + "");
        if (null == project) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("未找到项目，无法新增记录");
            return GsonUtil.gsonToString(result);
        }
        IPRecord ipRecord = new IPRecord();
        ipRecord.setProjectId(projectId);
        ipRecord.setRecordName(recordName);

        ipRecord.setMainIP(mainIP);
        ipRecord.setMainPort(mainPort);
        ipRecord.setMainMappingIP(mainMappingIP);
        ipRecord.setMainMappingPort(mainMappingPort);

        ipRecord.setMapUrl(mapUrl);
        ipRecord.setDescription(description);

        int row = ipRecordMapper.insertRecord(ipRecord);
        if (row == 1) {
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("新增记录成功");
            result.setData(ipRecord);
        } else {

            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("新增记录失败");
        }
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("修改记录")
    @RequestMapping(method = RequestMethod.POST, value = "update")
    public String updateRecord(HttpServletRequest request, HttpServletResponse response,
                               @ApiParam(value = "recordId（记录id）;\nrecordName（自定义记录名）;\n" +
                                       "mainIP（服务器ip地址，例如：192.168.0.37）;\n mainPort（服务器端口，例如：8080）;\n" +
                                       "mainMappingIP（映射后的ip地址，例如：10.10.0.1）;\n mainMappingPort（映射后的端口，例如：17001）;\n" +
                                       "mapUrl（地图url，例如：http://192.168.20.90:17002/map/china/zhongshan/zhongshanpgissltoandroid.xml）;\n" +
                                       "description（记录描述）")
                               @RequestBody RecordUpdate recordUpdate) {
        Long recordId = recordUpdate.getRecordId();
        String recordName = recordUpdate.getRecordName();
        String mainIP = recordUpdate.getMainIP();
        String mainPort = recordUpdate.getMainPort();
        String mainMappingIP = recordUpdate.getMainMappingIP();
        String mainMappingPort = recordUpdate.getMainMappingPort();
        String mapUrl = recordUpdate.getMapUrl();
        String description = recordUpdate.getDescription();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<IPRecord> result = new BaseResult<>();
        IPRecord ipRecord = ipRecordMapper.findRecordById(recordId + "");
        if (null == ipRecord) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("未找到该记录，无法修改");
            return GsonUtil.gsonToString(result);
        }
        ipRecord.setRecordName(recordName);

        ipRecord.setMainIP(mainIP);
        ipRecord.setMainPort(mainPort);
        ipRecord.setMainMappingIP(mainMappingIP);
        ipRecord.setMainMappingPort(mainMappingPort);

        ipRecord.setMapUrl(mapUrl);
        ipRecord.setDescription(description);

        int row = ipRecordMapper.updateRecord(ipRecord);
        if (row == 1) {
            ipRecord.setUpdateTime(DateUtil.getDateByFormat(null));
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("更新记录成功");
            result.setData(ipRecord);
        } else {

            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("更新记录失败");
        }
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("删除单挑记录")
    @RequestMapping(method = RequestMethod.POST, value = "delete")
    public String deleteRecord(HttpServletRequest request, HttpServletResponse response,
                               @ApiParam(value = "记录id") @RequestBody RecordDelete recordDelete) {
        Long recordId = recordDelete.getRecordId();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<String> result = new BaseResult<>();
        IPRecord ipRecord = ipRecordMapper.findRecordById(recordId + "");
        if (null == ipRecord) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该记录已经被删除");
            return GsonUtil.gsonToString(result);
        }
        int row = ipRecordMapper.deleteRecord(recordId + "");
        logger.info("删除记录数据行数：" + row);
        if (row > 0) {
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("删除记录成功");
        } else {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("删除记录失败");
        }
        return GsonUtil.gsonToString(result);
    }
}
