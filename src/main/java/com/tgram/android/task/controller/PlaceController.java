package com.tgram.android.task.controller;

import com.tgram.android.task.bean.BaseResult;
import com.tgram.android.task.bean.Place;
import com.tgram.android.task.bean_request.PlaceDelete;
import com.tgram.android.task.bean_request.PlaceInsert;
import com.tgram.android.task.bean_request.PlaceUpdate;
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
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
@RestController
@RequestMapping("/api/place")
@Api(value = "地市相关接口", description = "地市相关服务API")
public class PlaceController extends BaseController {

    @ApiOperation("查询所有地市")
    @RequestMapping(method = RequestMethod.POST, value = "findAll")
    public String findAllPlace(HttpServletRequest request, HttpServletResponse response) {
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        List<Place> list = placeMapper.findAllPlace();
        BaseResult<List<Place>> result = new BaseResult<>();
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("查询成功");
        result.setData(list);
        return GsonUtil.gsonToString(result);
    }

    /**
     * 新增地市
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("新增地市")
    @RequestMapping(method = RequestMethod.POST, value = "insert")
    public String insertPlace(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody PlaceInsert placeInsert) {
        String name = placeInsert.getName();
        String description = placeInsert.getDescription();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<Place> result = new BaseResult<>();
        if (StringUtil.isEmpty(name)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("名称不能为空");
            return GsonUtil.gsonToString(result);
        }
        Place list = placeMapper.findPlaceByName(name);
        if (null != list) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该名称已存在");
            return GsonUtil.gsonToString(result);
        }

        if (StringUtil.isEmpty(description)) {
            description = "";
        }
        Place place = new Place();
        place.setPlaceName(name);
        place.setDescription(description);
        int row = placeMapper.insertPlace(place);
        logger.info("新增地市影响行数：" + row);
        if (row == 1) {
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("新增成功");
            result.setData(place);
        } else {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("新增失败");
        }
        return GsonUtil.gsonToString(result);
    }

    /**
     * 修改地市信息
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("根据地市id，修改地市名称和描述信息")
    @RequestMapping(method = RequestMethod.POST, value = "update")
    public String updatePlace(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody PlaceUpdate placeUpdate) {
        Long id = placeUpdate.getId();
        String name = placeUpdate.getName();
        String description = placeUpdate.getDescription();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<Place> result = new BaseResult<>();
        Place place = placeMapper.findPlaceById(id + "");
        if (null == place) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("未查到该地市信息");
            return GsonUtil.gsonToString(result);
        }
        // 先根据name查找，看之前数据库是否已经存在相同的地市名
        if (StringUtil.isEmpty(name)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("名称不能为空");
            return GsonUtil.gsonToString(result);
        }
        Place list = placeMapper.findPlaceByName(name);
        if (null != list && list.getPlaceId() != place.getPlaceId()) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该名称已存在");
            return GsonUtil.gsonToString(result);
        }
        if (StringUtil.isEmpty(description)) {
            description = "";
        }
        place.setPlaceName(name);
        place.setDescription(description);
        int row = placeMapper.updatePlace(place);
        logger.info("修改地市影响行数：" + row);
        if (row == 1) {
            place.setUpdateTime(DateUtil.getDateByFormat(null));
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("修改成功");
            result.setData(place);
        } else {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("修改失败");
        }
        return GsonUtil.gsonToString(result);
    }

    @ApiOperation("删除地市，以及关联的项目和记录")
    @RequestMapping(method = RequestMethod.POST, value = "delete")
    public String delete(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody PlaceDelete placeDelete) {
        Long placeId = placeDelete.getPlaceId();
        // 验证token
        BaseResult<String> tokenResult = isTokenOver(request);
        if (!SysCode.SUCCESS_CODE.equals(tokenResult.getCode())) {
            // token验证失败
            return GsonUtil.gsonToString(tokenResult);
        }
        BaseResult<String> result = new BaseResult<>();
        Place place = placeMapper.findPlaceById(placeId + "");
        if (null == place) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("该数据已经被删除");
            return GsonUtil.gsonToString(result);
        }
        int row = placeMapper.deletePlace(placeId + "");
        logger.info("删除地市数据行数：" + row);
        if (row > 0) {
            result.setCode(SysCode.SUCCESS_CODE);
            result.setMsg("删除成功");
        } else {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("删除失败");
        }
        return GsonUtil.gsonToString(result);
    }

}
