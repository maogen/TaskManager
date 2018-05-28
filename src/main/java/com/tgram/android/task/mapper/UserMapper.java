package com.tgram.android.task.mapper;

import com.tgram.android.task.bean.LoginResult;
import com.tgram.android.task.bean.Token;

import java.util.List;

/**
 * 用户表操作
 */
public interface UserMapper {

    /**
     * 根据警号或者用户名查找用户
     *
     * @param username
     * @return
     */
    List<LoginResult> findUserByName(String username);
    /**
     * 根据用户名获取token
     */
    Token findTokenByName(String username);
    /**
     * 新增token
     * @param token
     * @return
     */
    int insertToken(Token token);
    /**
     * 更新token
     */
    int updateToken(Token token);
    /**
     * 根据token查询记录是否存在
     * @param token
     * @return
     */
    Token findTokenByToken(String token);
}
