package guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/16 18:11
 */
@Data
public class CinemaInfoVO implements Serializable {
    private Integer cinemaId;
    private String imgUrl;
    private String cinemaName;
    private String cinemaAdress;
    private String cinemaPhone;
}
