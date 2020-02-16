package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmT;
import guns.api.film.vo.FilmDetailVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author Seven.Lin
 * @since 2020-02-06
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {
    FilmDetailVo findFilmDetailByName(@Param("filmName") String filmName);

    FilmDetailVo findFilmDetailById(@Param("uuid") String uuid);

}
