package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/9 17:01
 */
@Data
public class ImgVo implements Serializable {
    private String mainImg;
    private String img01;
    private String img02;
    private String img03;
    private String img04;
}
