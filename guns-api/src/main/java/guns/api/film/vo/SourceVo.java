package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/6 17:08
 */
@Data
public class SourceVo implements Serializable {
    private String sourceId;
    private String sourceName;
    private boolean isActive;
}
