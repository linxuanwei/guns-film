package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/9 17:00
 */
@Data
public class FilmDescVo implements Serializable {
    private String biography;
    private String filmId;
    private ActorsVo actors;
}
