package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/9 12:11
 */
@Data
public class FilmDetailVo implements Serializable {
    private String filmId;
    private String filmName;
    private String filmEnName;
    private String imgAddress;
    private String score;
    private String scoreNum;
    private String totalBox;
    private String info01;
    private String info02;
    private String info03;
    private FilmDescVo info04;
    private String[] imgs;

}
