package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmActorT;
import guns.api.film.vo.ActorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 影片与演员映射表 Mapper 接口
 * </p>
 *
 * @author Seven.Lin
 * @since 2020-02-06
 */
public interface MoocFilmActorTMapper extends BaseMapper<MoocFilmActorT> {
    List<ActorVo> findActors(@Param("filmId") String filmId);
}
