package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Seven.Lin
 * @date 2020/2/9 17:05
 */
@Data
public class ActorsVo implements Serializable {
    private ActorVo director;
    private List<ActorVo> actors;
}
