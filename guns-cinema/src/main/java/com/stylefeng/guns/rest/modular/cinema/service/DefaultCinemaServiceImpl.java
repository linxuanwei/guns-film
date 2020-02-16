package com.stylefeng.guns.rest.modular.cinema.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.MoocCinemaT;
import guns.api.cinema.ICinemaService;
import guns.api.cinema.vo.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 12:11
 */
@Component
@Service(interfaceClass = ICinemaService.class)
public class DefaultCinemaServiceImpl implements ICinemaService {

    @Resource
    private MoocAreaDictTMapper moocAreaDictTMapper;
    @Resource
    private MoocBrandDictTMapper moocBrandDictTMapper;
    @Resource
    private MoocCinemaTMapper moocCinemaTMapper;
    @Resource
    private MoocHallDictTMapper moocHallDictTMapper;
    @Resource
    private MoocFieldTMapper moocFieldTMapper;
    @Resource
    private MoocHallFilmInfoTMapper moocHallFilmInfoTMapper;

    @Override
    public Page<CinemaVo> getCinemas(CinemaQueryVo cinemaQueryVo) {
        Page<MoocCinemaT> page = new Page<>(cinemaQueryVo.getNowPage(), cinemaQueryVo.getPageSize());
        EntityWrapper<MoocCinemaT> entityWrapper = new EntityWrapper<>();
        if (cinemaQueryVo.getBrandId() != 99) {
            entityWrapper.eq("brand_id", cinemaQueryVo.getBrandId());
        }
        if (cinemaQueryVo.getDistrictId() != 99) {
            entityWrapper.eq("area_id", cinemaQueryVo.getDistrictId());
        }
        if (cinemaQueryVo.getHallType() != 99) {
            entityWrapper.like("hall_ids", "%#" + cinemaQueryVo.getHallType() + "#%");
        }
        List<CinemaVo> listCinema = new ArrayList<>();
        List<MoocCinemaT> moocCinemaTS = moocCinemaTMapper.selectPage(page, entityWrapper);
        for (MoocCinemaT moocCinemaT : moocCinemaTS) {
            CinemaVo cinemaVo = CinemaVo.builder().
                    cinemaName(moocCinemaT.getCinemaName()).
                    address(moocCinemaT.getCinemaAddress()).
                    uuid(String.valueOf(moocCinemaT.getUuid())).
                    minimumPrice(String.valueOf(moocCinemaT.getMinimumPrice())).
                    build();

            listCinema.add(cinemaVo);
        }
        int counts = moocCinemaTMapper.selectCount(entityWrapper);
        Page<CinemaVo> result = new Page<>();
        result.setRecords(listCinema);
        result.setTotal(counts);
        result.setSize(cinemaQueryVo.getPageSize());
        return result;
    }

    @Override
    public List<BrandVO> getBrands(Integer brandId) {
        return null;
    }

    @Override
    public List<AreaVO> getAreas(Integer areaId) {
        return null;
    }

    @Override
    public List<HallTypeVO> getHallTypes(Integer hallType) {
        return null;
    }

    @Override
    public CinemaInfoVO getCinemaInfo(Integer cinemaId) {
        return null;
    }

    @Override
    public FilmInfoVO getFilmInfoByCinemaId(Integer cinemaId) {
        return null;
    }

    @Override
    public FilmFieldVO getFilmFieldInfo(Integer fileldId) {
        return null;
    }

    @Override
    public FilmInfoVO getFilmInfoByFieldId(Integer fileldId) {
        return null;
    }
}
