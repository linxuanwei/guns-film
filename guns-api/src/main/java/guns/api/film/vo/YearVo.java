package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/6 17:09
 */
@Data
public class YearVo implements Serializable {
    private String yearId;
    private String yearName;
    private boolean isActive;
}
