package com.stylefeng.guns.rest.modular.film.vo;

import guns.api.film.vo.BannerVo;
import guns.api.film.vo.FilmInfo;
import guns.api.film.vo.FilmVo;
import lombok.Data;

import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 11:28
 */
@Data
public class FilmIndexVo {
    private List<BannerVo> banners;
    private FilmVo hotFilms;
    private FilmVo soonFilms;
    private List<FilmInfo> boxRanking;
    private List<FilmInfo> expectRanking;
    private List<FilmInfo> top100;
}
