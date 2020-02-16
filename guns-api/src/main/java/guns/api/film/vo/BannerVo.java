package guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Seven.Lin
 * @date 2020/2/6 11:22
 */
@Data
public class BannerVo implements Serializable {
    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;
}
