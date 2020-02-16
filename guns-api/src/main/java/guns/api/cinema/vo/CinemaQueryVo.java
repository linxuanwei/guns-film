package guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/16 17:40
 */
@Data
public class CinemaQueryVo implements Serializable {
    private Integer brandId = 99;
    private Integer hallType = 99;
    private Integer districtId = 99;
    private Integer pageSize = 12;
    private Integer nowPage = 1;

}
