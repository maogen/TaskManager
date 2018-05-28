package com.tgram.android.task.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 项目名称：TaskCheck
 * 类描述：json工具
 * 创建人：mzgkq
 * 创建时间：18/3/9
 */
public class GsonUtil {

    /**
     * 将对象转成json字符串
     *
     * @param obj
     * @return
     */
    public static String gsonToString(Object obj) {
        return new Gson().toJson(obj);
    }

    /**
     * 将json字符串转为对象
     *
     * @param <T>
     * @return
     */
    public static <T> T stringToGson(String json, TypeToken<T> token) {
        try {
            return new Gson().fromJson(json, token.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
