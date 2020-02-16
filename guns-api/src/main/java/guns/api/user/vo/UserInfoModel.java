package guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Seven.Lin
 * @date 2020/2/4 12:09
 */
@Data
public class UserInfoModel implements Serializable {
    private Integer uuid;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private int sex;
    private String birthday;
    private Integer lifeState;
    private String biography;
    private String headUrl;
    private Date beginTime;
    private Date updateTime;


}
