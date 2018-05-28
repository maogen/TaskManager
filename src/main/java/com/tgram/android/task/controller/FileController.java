package com.tgram.android.task.controller;

import com.tgram.android.task.bean.BaseResult;
import com.tgram.android.task.bean.FileUploadResult;
import com.tgram.android.task.utils.FileUtil;
import com.tgram.android.task.utils.GsonUtil;
import com.tgram.android.task.utils.StringUtil;
import com.tgram.android.task.utils.SysCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 项目名称：TaskManager
 * 类描述：文件处理接口
 * 创建人：mzgkq
 * 创建时间：2018/4/2
 */
@RestController
@RequestMapping(value = "/api/file")
@Api(value = "文件相关接口", description = "文件相关服务API")
public class FileController extends BaseController {

    @ApiOperation("得到测试文件路劲测试")
    @RequestMapping(method = RequestMethod.GET, value = "testFile")
    public void testFile(HttpServletRequest request, HttpServletResponse response) {
        String fileStr = "file/file1.docx";
        try {
            request.getRequestDispatcher("/" + fileStr).forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @ApiOperation("单个文件上传接口")
    @RequestMapping(method = RequestMethod.POST, value = "upload")
    public String upload(HttpServletRequest request, HttpServletResponse response,
                         MultipartFile file) {
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<FileUploadResult> result = new BaseResult<>();

        if (null == file) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("文件是空，无法保存");
            return GsonUtil.gsonToString(result);
        }
        String saveName = FileUtil.saveFile(myWebConfig, file);
        if (StringUtil.isEmpty(saveName)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("保存文件失败");
            return GsonUtil.gsonToString(result);
        }
        FileUploadResult fileResult = new FileUploadResult();
        String uuid = UUID.randomUUID().toString();
        fileResult.setId(uuid);
        fileResult.setName(saveName);
//        fileResult.setUrl(myWebConfig.getSaveUrl() + saveName);
        result.setData(fileResult);
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("保存成功");
        return GsonUtil.gsonToString(result);

    }

    @ApiOperation("多个文件上传接口")
    @RequestMapping(method = RequestMethod.POST, value = "batchUpload")
    public String batchUpload(HttpServletRequest request, HttpServletResponse response) {
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<List<FileUploadResult>> result = new BaseResult<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

        if (null == files) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("文件是空，无法保存");
            return GsonUtil.gsonToString(result);
        }
        List<FileUploadResult> resultList = new ArrayList<>();
        for (MultipartFile file : files) {
            String saveName = FileUtil.saveFile(myWebConfig, file);
            if (StringUtil.isEmpty(saveName)) {
                result.setCode(SysCode.FAILED_CODE);
                result.setMsg("保存文件失败");
                return GsonUtil.gsonToString(result);
            }
            FileUploadResult fileResult = new FileUploadResult();
            String uuid = UUID.randomUUID().toString();
            fileResult.setId(uuid);
            fileResult.setName(saveName);
//            fileResult.setUrl(myWebConfig.getSaveUrl() + saveName);
            resultList.add(fileResult);
        }
        result.setData(resultList);
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("保存成功");
        return GsonUtil.gsonToString(result);
    }

}
