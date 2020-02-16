package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/9 17:02
 */
@Data
public class ActorVo implements Serializable {
    private String imgAddress;
    private String directorName;
    private String roleName;
}
