package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVo;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVo;
import com.stylefeng.guns.rest.modular.film.vo.FilmRequestVo;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import guns.api.film.IFilmServiceApi;
import guns.api.film.vo.CatVo;
import guns.api.film.vo.FilmDetailVo;
import guns.api.film.vo.FilmVo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 16:40
 */
@RestController
@RequestMapping("/film")
public class FilmController {
    @Reference(interfaceClass = IFilmServiceApi.class)
    private IFilmServiceApi filmServiceApi;

    @GetMapping("/getIndex")
    public ResponseVo getIndex() {
        FilmIndexVo filmIndexVo = new FilmIndexVo();
        filmIndexVo.setBanners(filmServiceApi.getBanners());
        filmIndexVo.setBoxRanking(filmServiceApi.getBoxRanking());
        filmIndexVo.setExpectRanking(filmServiceApi.getExpectRanking());
        filmIndexVo.setHotFilms(filmServiceApi.getHotFilms(true, 10, 1, 1, 99, 99, 99));
        filmIndexVo.setSoonFilms(filmServiceApi.getSoonFilms(true, 10, 1, 1, 99, 99, 99));
        filmIndexVo.setTop100(filmIndexVo.getTop100());
        return ResponseVo.success(filmIndexVo, true);
    }

    @GetMapping("/getConditionList")
    public ResponseVo getConditionList(@RequestParam(name = "catId", defaultValue = "99") String catId,
                                       @RequestParam(name = "sourceId", defaultValue = "99") String sourceId,
                                       @RequestParam(name = "yearId", defaultValue = "99") String yearId) {

        // 类型集合
        boolean flag = false;
        List<CatVo> catVos = filmServiceApi.getCats();
        List<CatVo> catResult = new ArrayList<>();
        CatVo cat = null;
        for (CatVo catVo : catVos) {
            String catId_ = catVo.getCatId();
            // 99 - 全部
            if (catId_.equals("99")) {
                cat = catVo;
                continue;
            }
            if (catId_.equals(catId)) {
                flag = true;
                catVo.setActive(true);
            }
            catResult.add(catVo);
        }
        if (cat != null) {
            if (!flag) {
                cat.setActive(true);
            }
            catResult.add(cat);
        }

        FilmConditionVo filmConditionVo = new FilmConditionVo();
        filmConditionVo.setCatInfo(catResult);


        // 片源集合

        // 年代集合
        return ResponseVo.success(filmConditionVo, true);
    }


    @GetMapping("/getFilms")
    public ResponseVo getFilms(FilmRequestVo filmRequestVo) {
        FilmVo filmVo = new FilmVo();
        switch (filmRequestVo.getShowType()) {
            case 2:
                filmVo = filmServiceApi.getSoonFilms(false, filmRequestVo.getPagesize(),
                        filmRequestVo.getNowPage(), filmRequestVo.getSortId(), filmRequestVo.getCatId(), filmRequestVo.getSourceId(),
                        filmRequestVo.getYearId());
                break;
            case 3:
                filmVo = filmServiceApi.getClassicFilms(filmRequestVo.getPagesize(),
                        filmRequestVo.getNowPage(), filmRequestVo.getSortId(), filmRequestVo.getCatId(), filmRequestVo.getSourceId(),
                        filmRequestVo.getYearId());
                break;
            case 1:
            default:
                filmVo = filmServiceApi.getHotFilms(false, filmRequestVo.getPagesize(),
                        filmRequestVo.getNowPage(), filmRequestVo.getSortId(), filmRequestVo.getCatId(), filmRequestVo.getSourceId(),
                        filmRequestVo.getYearId());
        }
        return ResponseVo.success(filmVo.getFilmInfo(), true, filmRequestVo.getNowPage(), filmVo.getTotalPages());

    }


    @GetMapping("/films/{filmParam}")
    public ResponseVo getFilmInfo(@PathVariable String filmParam, Integer searchType) {
        FilmDetailVo filmDetailVo = filmServiceApi.getFilmDetail(searchType, filmParam);
        return ResponseVo.success(filmDetailVo, true);
    }
}
