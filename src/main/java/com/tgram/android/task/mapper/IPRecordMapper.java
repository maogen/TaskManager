package com.tgram.android.task.mapper;

import com.tgram.android.task.bean.IPRecord;

import java.util.List;

/**
 * 项目名称：TaskManager
 * 类描述：
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public interface IPRecordMapper {
    /**
     * 根据项目id查询关联的ip、端口记录
     *
     * @param projectId
     * @return
     */
    List<IPRecord> findRecordByProject(String projectId);

    /**
     * 根据记录id查询记录信息
     *
     * @param recordId
     * @return
     */
    IPRecord findRecordById(String recordId);

    /**
     * 新增记录
     *
     * @param ipRecord
     * @return
     */
    int insertRecord(IPRecord ipRecord);

    /**
     * 修改记录
     *
     * @param ipRecord
     * @return
     */
    int updateRecord(IPRecord ipRecord);

    /**
     * 删除记录
     *
     * @param recordId
     * @return
     */
    int deleteRecord(String recordId);

}
