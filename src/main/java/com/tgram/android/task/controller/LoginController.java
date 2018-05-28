package com.tgram.android.task.controller;

import com.tgram.android.task.bean.BaseResult;
import com.tgram.android.task.bean.LoginResult;
import com.tgram.android.task.bean.Token;
import com.tgram.android.task.bean_request.LoginUser;
import com.tgram.android.task.mapper.UserMapper;
import com.tgram.android.task.utils.GsonUtil;
import com.tgram.android.task.utils.StringUtil;
import com.tgram.android.task.utils.SysCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * 项目名称：TaskCheck
 * 类描述：登录
 * 创建人：mzgkq
 * 创建时间：18/3/9
 */
@RestController
@Api(value = "登录接口", description = "登录服务API")
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);


    @Autowired
    UserMapper userMapper;

    @Autowired
    MyWebConfig myWebConfig;

    @RequestMapping(method = RequestMethod.GET, value = "hello")
    public void hello() {
        logger.info("saveDir:" + myWebConfig.getSaveDir());
    }

    @ApiOperation("登录")
    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String login(@RequestBody LoginUser loginUser) {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        BaseResult<LoginResult> result = new BaseResult<LoginResult>();
        if (StringUtil.isEmpty(username)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("用户名不能为空");
            return GsonUtil.gsonToString(result);
        }
        if (StringUtil.isEmpty(password)) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("密码不能为空");
            return GsonUtil.gsonToString(result);
        }
        logger.info("username：" + username);
        logger.info("password：" + password);
        List<LoginResult> list = userMapper.findUserByName(username);
        if (null == list || list.size() == 0) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("未找到用户");
            return GsonUtil.gsonToString(result);
        }
        if (list.size() > 1) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("用户不唯一");
            return GsonUtil.gsonToString(result);
        }
        LoginResult loginResult = list.get(0);
        if (!password.equals(loginResult.getPassword())) {
            result.setCode(SysCode.FAILED_CODE);
            result.setMsg("密码错误");
            return GsonUtil.gsonToString(result);
        }
        // 密码正确，获取token
        String tokenStr = UUID.randomUUID().toString();
        Token token = userMapper.findTokenByName(username);
        if (null == token) {
            token = new Token();
            token.setUsername(username);
            token.setToken(tokenStr);
            userMapper.insertToken(token);
        } else {
            token.setToken(tokenStr);
            userMapper.updateToken(token);
        }
        result.setCode(SysCode.SUCCESS_CODE);
        result.setMsg("登录成功");
        loginResult.setToken(tokenStr);
        result.setData(loginResult);
        return GsonUtil.gsonToString(result);
    }
}
