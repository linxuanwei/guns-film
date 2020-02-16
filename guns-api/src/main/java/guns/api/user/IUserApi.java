package guns.api.user;

import guns.api.user.vo.UserInfoModel;
import guns.api.user.vo.UserModel;

/**
 * @author Seven.Lin
 * @date 2020/1/22 18:14
 */
public interface IUserApi {
    int login(String name, String password);

    boolean registry(UserModel userModal);

    boolean checkUsername(String username);

    UserInfoModel getUserInfoModel(int userId);

    UserInfoModel updateUserInfoModel(UserInfoModel userInfoModel);

}
