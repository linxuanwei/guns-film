package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/6 17:07
 */
@Data
public class CatVo implements Serializable {
    private String catId;
    private String catName;
    private boolean isActive;
}
