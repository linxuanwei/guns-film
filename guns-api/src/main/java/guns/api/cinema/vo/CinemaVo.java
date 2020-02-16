package guns.api.cinema.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/16 18:05
 */
@Data
@Builder
public class CinemaVo implements Serializable {
    private String uuid;
    private String cinemaName;
    private String address;

    private String minimumPrice;
}
