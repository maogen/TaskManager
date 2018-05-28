package com.tgram.android.task.utils;

/**
 * 项目名称：TaskCheck
 * 类描述：字符串工具类
 * 创建人：mzgkq
 * 创建时间：18/3/9
 */
public class StringUtil {
    /**
     * 字符串是空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (null == string || "".equals(string)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
}
