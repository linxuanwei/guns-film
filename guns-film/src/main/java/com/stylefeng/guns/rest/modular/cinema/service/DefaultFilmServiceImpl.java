package com.stylefeng.guns.rest.modular.cinema.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import guns.api.film.IFilmServiceApi;
import guns.api.film.vo.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 12:11
 */
@Component
@Service(interfaceClass = IFilmServiceApi.class)
public class DefaultFilmServiceImpl implements IFilmServiceApi {
    @Resource
    private MoocBannerTMapper bannerTMapper;
    @Resource
    private MoocFilmTMapper moocFilmTMapper;
    @Resource
    private MoocCatDictTMapper moocCatDictTMapper;
    @Resource
    private MoocSourceDictTMapper moocSourceDictTMapper;
    @Resource
    private MoocYearDictTMapper moocYearDictTMapper;
    @Resource
    private MoocFilmInfoTMapper moocFilmInfoTMapper;
    @Resource
    private MoocActorTMapper moocActorTMapper;
    @Resource
    private MoocFilmActorTMapper moocFilmActorTMapper;


    @Override
    public List<BannerVo> getBanners() {
        List<BannerVo> result = new ArrayList<>();
        List<MoocBannerT> list = bannerTMapper.selectList(null);
        for (MoocBannerT banner : list) {
            BannerVo bannerVo = new BannerVo();
            bannerVo.setBannerAddress(banner.getBannerAddress());
            bannerVo.setBannerId(banner.getUuid() + "");
            bannerVo.setBannerUrl(banner.getBannerUrl());
            result.add(bannerVo);
        }

        return result;
    }


    @Override
    public FilmVo getClassicFilms(int nums, Integer nowPage, Integer sortId, Integer catId, Integer sourceId, Integer yearId) {
        FilmVo filmVo = new FilmVo();
        List<FilmInfo> filmInfoList;

        EntityWrapper<MoocFilmT> condition = new EntityWrapper<>();
        condition.eq("film_status", 3);
        Page<MoocFilmT> page = null;
        switch (sortId) {
            case 2:
                page = new Page<>(nowPage, nums, "film_time");
                break;
            case 3:
                page = new Page<>(nowPage, nums, "film_score");
                break;
            default:
                page = new Page<>(nowPage, nums, "film_box_office");
                break;
        }
        if (sourceId != 99) {
            condition.eq("film_source", sourceId);
        }
        if (yearId != 99) {
            condition.eq("film_date", yearId);
        }
        if (catId != 99) {
            String catStr = "%#" + catId + "#%";
            condition.like("film_cats", catStr);
        }
        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
        filmInfoList = this.convertFilmInfo(moocFilmTS);
        filmVo.setFilmInfo(filmInfoList);
        filmVo.setFilmNum(filmInfoList.size());

        int totalCounts = moocFilmTMapper.selectCount(condition);
        int totalPages = 1 + totalCounts / nums;
        filmVo.setTotalPages(totalPages);
        filmVo.setNowPage(nowPage);
        return filmVo;
    }

    private List<FilmInfo> convertFilmInfo(List<MoocFilmT> moocFilmTS) {
        List<FilmInfo> filmInfoList = new ArrayList<>();
        if (moocFilmTS == null) {
            return filmInfoList;
        }
        for (MoocFilmT moocFilmT : moocFilmTS) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));
            filmInfo.setScore(moocFilmT.getFilmScore());
            filmInfo.setImgAddress(moocFilmT.getImgAddress());
            filmInfo.setFilmType(moocFilmT.getFilmType());
            filmInfo.setFilmScore(moocFilmT.getFilmScore());
            filmInfo.setFilmName(moocFilmT.getFilmName());
            filmInfo.setFilmId(String.valueOf(moocFilmT.getUuid()));
            filmInfo.setExpectNum(moocFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(moocFilmT.getFilmBoxOffice());
            filmInfoList.add(filmInfo);
        }
        return filmInfoList;
    }

    @Override
    public FilmVo getHotFilms(boolean isLimit, int nums, Integer nowPage, Integer sortId, Integer catId, Integer sourceId, Integer yearId) {
        FilmVo filmVo = new FilmVo();
        List<FilmInfo> filmInfoList;

        EntityWrapper<MoocFilmT> condition = new EntityWrapper<>();
        condition.eq("film_status", 1);

        // 有条数限制
        if (isLimit) {
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(null, condition);
            filmInfoList = this.convertFilmInfo(moocFilmTS);
            filmVo.setFilmInfo(filmInfoList);
            filmVo.setFilmNum(filmInfoList.size());

        } else {
            // 分页
            Page<MoocFilmT> page = null;
            switch (sortId) {
                case 2:
                    page = new Page<>(nowPage, nums, "film_time");
                    break;
                case 3:
                    page = new Page<>(nowPage, nums, "film_score");
                    break;
                default:
                    page = new Page<>(nowPage, nums, "film_box_office");
                    break;
            }

            if (sourceId != 99) {
                condition.eq("film_source", sourceId);
            }
            if (yearId != 99) {
                condition.eq("film_date", yearId);
            }
            if (catId != 99) {
                String catStr = "%#" + catId + "#%";
                condition.like("film_cats", catStr);
            }
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
            filmInfoList = this.convertFilmInfo(moocFilmTS);
            filmVo.setFilmInfo(filmInfoList);
            filmVo.setFilmNum(filmInfoList.size());

            int totalCounts = moocFilmTMapper.selectCount(condition);
            int totalPages = 1 + totalCounts / nums;
            filmVo.setTotalPages(totalPages);
            filmVo.setNowPage(nowPage);

        }

        return filmVo;

    }

    @Override
    public FilmVo getSoonFilms(boolean isLimit, int nums, Integer nowPage, Integer sortId, Integer catId, Integer sourceId, Integer yearId) {
        FilmVo filmVo = new FilmVo();
        List<FilmInfo> filmInfoList;

        EntityWrapper<MoocFilmT> condition = new EntityWrapper<>();
        condition.eq("film_status", 2);

        // 有条数限制
        if (isLimit) {
            Page<MoocFilmT> page = null;
            if (sortId == 3) {
                page = new Page<>(nowPage, nums, "film_score");
            } else {
                page = new Page<>(nowPage, nums, "film_preSaleNum");
            }
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
            filmInfoList = this.convertFilmInfo(moocFilmTS);
            filmVo.setFilmInfo(filmInfoList);
            filmVo.setFilmNum(filmInfoList.size());
        } else {
            Page<MoocFilmT> page = new Page<>(nowPage, nums);
            if (sourceId != 99) {
                condition.eq("film_source", sourceId);
            }
            if (yearId != 99) {
                condition.eq("film_date", yearId);
            }
            if (catId != 99) {
                String catStr = "%#" + catId + "#%";
                condition.like("film_cats", catStr);
            }
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
            filmInfoList = this.convertFilmInfo(moocFilmTS);
            filmVo.setFilmInfo(filmInfoList);
            filmVo.setFilmNum(filmInfoList.size());

            int totalCounts = moocFilmTMapper.selectCount(condition);
            int totalPages = 1 + totalCounts / nums;
            filmVo.setTotalPages(totalPages);
            filmVo.setNowPage(nowPage);
        }

        return filmVo;
    }

    // 正在上映的，票房前十名
    @Override
    public List<FilmInfo> getBoxRanking() {
        EntityWrapper<MoocFilmT> condition = new EntityWrapper<>();
        condition.eq("film_status", 1);
        Page<MoocFilmT> page = new Page<>(1, 10, "film_box_office");
        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
        return this.convertFilmInfo(moocFilmTS);
    }

    // 期待上映的，预售前十名
    @Override
    public List<FilmInfo> getExpectRanking() {
        EntityWrapper<MoocFilmT> condition = new EntityWrapper<>();
        condition.eq("film_status", 2);
        Page<MoocFilmT> page = new Page<>(1, 10, "film_preSaleNum");
        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
        return this.convertFilmInfo(moocFilmTS);
    }

    // 正在上映的经典影片，评分前十名
    @Override
    public List<FilmInfo> getTop() {
        EntityWrapper<MoocFilmT> condition = new EntityWrapper<>();
        condition.eq("film_status", 1);
        Page<MoocFilmT> page = new Page<>(1, 10, "film_score");
        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, condition);
        return this.convertFilmInfo(moocFilmTS);
    }

    @Override
    public List<CatVo> getCats() {
        List<MoocCatDictT> moocCatDictTS = moocCatDictTMapper.selectList(null);
        List<CatVo> list = new ArrayList<>();
        for (MoocCatDictT catDictT : moocCatDictTS) {
            CatVo catVo = new CatVo();
            catVo.setCatId(catDictT.getUuid() + "");
            catVo.setCatName(catDictT.getShowName());
            list.add(catVo);
        }
        return list;
    }

    @Override
    public List<SourceVo> getSources() {
        List<MoocSourceDictT> moocSourceDictTS = moocSourceDictTMapper.selectList(null);
        List<SourceVo> sourceVoList = new ArrayList<>();
        for (MoocSourceDictT moocSourceDictT : moocSourceDictTS) {
            SourceVo sourceVo = new SourceVo();
            sourceVo.setSourceId(moocSourceDictT.getUuid() + "");
            sourceVo.setSourceName(moocSourceDictT.getShowName());
            sourceVoList.add(sourceVo);
        }
        return sourceVoList;
    }

    @Override
    public List<YearVo> getYears() {
        List<MoocYearDictT> moocYearDictTS = moocYearDictTMapper.selectList(null);
        List<YearVo> list = new ArrayList<>();
        for (MoocYearDictT yearDictT : moocYearDictTS) {
            YearVo yearVo = new YearVo();
            yearVo.setYearId(yearDictT.getUuid() + "");
            yearVo.setYearName(yearDictT.getShowName());
            list.add(yearVo);
        }
        return list;
    }

    @Override
    public FilmDetailVo getFilmDetail(Integer searchType, String searchParam) {
        FilmDetailVo filmDetailVo = null;
        String searchPattern = "%" + searchParam + "%";
        // searchType 1-按名称查找  2-按id查询
        if (searchType == 1) {
            filmDetailVo = moocFilmTMapper.findFilmDetailByName(searchPattern);
        } else {
            filmDetailVo = moocFilmTMapper.findFilmDetailById(searchPattern);
        }
        if (filmDetailVo == null) {
            return null;
        }

        MoocFilmInfoT moocFilmInfoT = selectOne(filmDetailVo.getFilmId());
        filmDetailVo.setImgs(moocFilmInfoT.getFilmImgs().split(","));
        filmDetailVo.setInfo04(getFilmDesc(moocFilmInfoT));

        return filmDetailVo;
    }

    private MoocFilmInfoT selectOne(String filmId) {
        MoocFilmInfoT condition = new MoocFilmInfoT();
        condition.setFilmId(filmId);
        return moocFilmInfoTMapper.selectOne(condition);
    }

    private FilmDescVo getFilmDesc(MoocFilmInfoT moocFilmInfoT) {
        String filmId = moocFilmInfoT.getFilmId();
        MoocFilmInfoT filmInfoT = selectOne(filmId);
        FilmDescVo filmDescVo = new FilmDescVo();
        filmDescVo.setBiography(filmInfoT.getBiography());
        filmDescVo.setFilmId(filmId);

        ActorsVo actorsVo = new ActorsVo();
        MoocActorT moocDirector = moocActorTMapper.selectById(moocFilmInfoT.getDirectorId());
        ActorVo director = new ActorVo();
        director.setDirectorName(moocDirector.getActorName());
        director.setImgAddress(moocDirector.getActorImg());
        actorsVo.setDirector(director);

        List<ActorVo> actors = moocFilmActorTMapper.findActors(filmId);
        actorsVo.setActors(actors);
        filmDescVo.setActors(actorsVo);
        return filmDescVo;
    }


}
