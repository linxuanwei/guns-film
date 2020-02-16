package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/6 11:30
 */
@Data
public class FilmVo implements Serializable {
    private Integer filmNum;
    private Integer totalPages;
    private Integer nowPage;
    private List<FilmInfo> filmInfo;
}
