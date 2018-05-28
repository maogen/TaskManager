package com.tgram.android.task.utils;

import com.tgram.android.task.bean.FileUploadResult;
import com.tgram.android.task.controller.MyWebConfig;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.UUID;

/**
 * 项目名称：TaskManager
 * 类描述：文件工具
 * 创建人：mzgkq
 * 创建时间：2018/4/2
 */
public class FileUtil {


    /**
     * 保存文件到指定目录
     * @param webConfig
     * @param file
     * @return
     */
    public static String saveFile(MyWebConfig webConfig,MultipartFile file){
        String saveDir = webConfig.getSaveDir();
        return saveFile(saveDir,file);
    }
    /**
     * 保存文件
     *
     * @param file
     * @return
     */
    public static String saveFile(String path, MultipartFile file) {
        // 新建目录
        try {
            FileUtils.forceMkdir(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 文件名
        String name = file.getOriginalFilename();
        // 流操作
        BufferedOutputStream out = null;
        FileOutputStream fOut = null;
        // 返回值
        FileUploadResult result = new FileUploadResult();
        String uuid = UUID.randomUUID().toString();
        result.setId(uuid);
        result.setName(name);
        try {
            File saveFile = new File(path, name);
            fOut = new FileOutputStream(saveFile);
            out = new BufferedOutputStream(fOut);
            out.write(file.getBytes());
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭流
            if (null != fOut) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return name;
    }

}
