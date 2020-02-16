package com.stylefeng.guns.rest.modular.film.vo;

import guns.api.film.vo.CatVo;
import guns.api.film.vo.SourceVo;
import guns.api.film.vo.YearVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 17:25
 */
@Data
public class FilmConditionVo implements Serializable {
    List<CatVo> catInfo;
    List<SourceVo> sourceInfo;
    List<YearVo> yearInfo;
}
