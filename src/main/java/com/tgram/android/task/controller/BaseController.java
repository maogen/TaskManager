package com.tgram.android.task.controller;

import com.tgram.android.task.bean.BaseResult;
import com.tgram.android.task.bean.Token;
import com.tgram.android.task.mapper.IPRecordMapper;
import com.tgram.android.task.mapper.PlaceMapper;
import com.tgram.android.task.mapper.ProjectMapper;
import com.tgram.android.task.mapper.UserMapper;
import com.tgram.android.task.utils.DateUtil;
import com.tgram.android.task.utils.SysCode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称：TaskManager
 * 类描述：基础controller
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public class BaseController {
    /**
     * 是否开启token验证
     */
    private static boolean isOpenToken = false;
    /**
     * token过期时间（秒）
     */
    private static long tokenTime = 1 * 60 * 60;


    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected PlaceMapper placeMapper;
    @Autowired
    protected ProjectMapper projectMapper;
    @Autowired
    protected IPRecordMapper ipRecordMapper;
    // 配置文件
    @Autowired
    protected  MyWebConfig myWebConfig;

    protected Logger logger = Logger.getLogger(BaseController.class);

    /**
     * 判断token
     *
     * @return
     */
    protected BaseResult<String> isTokenOver(HttpServletRequest request) {
        String tokenHeader = request.getHeader("token");
        Token token = userMapper.findTokenByToken(tokenHeader);

        BaseResult<String> result = new BaseResult<>();
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("");
        if (!isOpenToken) {
            // 没有开启验证，直接返回成功
            return result;
        }
        if (null == token) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("用户未登录，请登陆后重试");
            return result;
        }
        String updateTime = token.getUpdateTime();
        String nowTime = DateUtil.getDateByFormat(null);
        long timeSpace = DateUtil.getDateSpace(nowTime, updateTime);
        if (timeSpace > tokenTime) {
            // token超时
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("用户登录超时，请重新登录");
            return result;
        }
        return result;

    }

}
