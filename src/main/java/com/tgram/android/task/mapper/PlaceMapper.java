package com.tgram.android.task.mapper;

import com.tgram.android.task.bean.Place;

import java.util.List;

/**
 * 项目名称：TaskManager
 * 类描述：地市接口
 * 创建人：mzgkq
 * 创建时间：18/3/22
 */
public interface PlaceMapper {
    /**
     * 查询所有地市
     *
     * @return
     */
    List<Place> findAllPlace();

    /**
     * 根据id查找地市
     *
     * @param placeId
     * @return
     */
    Place findPlaceById(String placeId);

    /**
     * 根据地市名称查询
     *
     * @param placeName
     * @return
     */
    Place findPlaceByName(String placeName);

    /**
     * 新增地址
     *
     * @param place
     * @return
     */
    int insertPlace(Place place);

    /**
     * 更新地址
     *
     * @param place
     * @return
     */
    int updatePlace(Place place);

    /**
     * 删除地市，地市关联的项目，关联的记录
     *
     * @param placeId
     * @return
     */
    int deletePlace(String placeId);
}
