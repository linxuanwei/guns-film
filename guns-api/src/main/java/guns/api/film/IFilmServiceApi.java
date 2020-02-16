package guns.api.film;

import guns.api.film.vo.*;

import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 11:46
 */
public interface IFilmServiceApi {
    List<BannerVo> getBanners();

    // 获取热映影片
    FilmVo getHotFilms(boolean isLimit, int nums, Integer nowPage, Integer sortId, Integer catId, Integer sourceId, Integer yearId);

    // 获取即将上映影片
    FilmVo getSoonFilms(boolean isLimit, int nums, Integer nowPage, Integer sortId, Integer catId, Integer sourceId, Integer yearId);

    // 获取经典影片
    FilmVo getClassicFilms(int nums, Integer nowPage, Integer sortId, Integer catId, Integer sourceId, Integer yearId);


    // 获取票房排行榜
    List<FilmInfo> getBoxRanking();

    // 获取人气排行榜
    List<FilmInfo> getExpectRanking();

    // 获取top100
    List<FilmInfo> getTop();

    // ==== 按条件查询影片 ====
    List<CatVo> getCats();

    List<SourceVo> getSources();

    List<YearVo> getYears();


    // 根据影片名称或Id查询影片详情
    FilmDetailVo getFilmDetail(Integer searchType, String searchParam);


}
