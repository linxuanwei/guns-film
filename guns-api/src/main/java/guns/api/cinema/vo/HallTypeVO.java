package guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/16 18:07
 */
@Data
public class HallTypeVO implements Serializable {
    private String halltypeId;
    private String halltypeName;
    private boolean isActive;
}
