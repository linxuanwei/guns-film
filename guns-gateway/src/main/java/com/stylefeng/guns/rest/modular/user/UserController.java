package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import guns.api.user.IUserApi;
import guns.api.user.vo.UserInfoModel;
import guns.api.user.vo.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Seven.Lin
 * @date 2020/2/4 16:30
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Reference(interfaceClass = IUserApi.class)
    private IUserApi userApi;

    @PostMapping("/register")
    public ResponseVo registry(UserModel userModel) {
        if (userModel == null) {
            return ResponseVo.serviceFail("注册参数为空");
        }
        if (userModel.getUsername().trim().length() == 0 || userModel.getPassword().trim().length() == 0) {
            return ResponseVo.serviceFail("用户名及密码不能为空");
        }
        boolean isSuccess = userApi.registry(userModel);
        if (isSuccess) {
            log.info("user:" + userModel.getUsername() + " 注册成功!");
            return ResponseVo.success(null, "注册成功");
        }
        return ResponseVo.serviceFail("注册失败");
    }


    @GetMapping("/logout")
    public ResponseVo logout() {
        return ResponseVo.success(null, "退出成功");
    }

    @GetMapping("/{userId}")
    public ResponseVo getUserInfo(@PathVariable Integer userId) {
        Integer currentUserId = CurrentUser.getCurrentUser();
        if (currentUserId == null || userId == null) {
            return ResponseVo.serviceFail("请先登录");
        }
        if (currentUserId.equals(userId)) {
            UserInfoModel userInfoModel = userApi.getUserInfoModel(userId);
            if (userInfoModel != null) {
                return ResponseVo.success(userInfoModel);
            }
        }
        return ResponseVo.serviceFail("查询用户:" + userId + " 失败");
    }

    @PutMapping(value = "/update", produces = "application/json;charset=UTF-8")
    public ResponseVo updateUserInfo(UserInfoModel userInfoModel) {
        System.out.println(userInfoModel);
        Integer userId = userInfoModel.getUuid();
        if (userId == null) {
            return ResponseVo.serviceFail("传入的userId不能为空");
        }
        Integer currentUserId = CurrentUser.getCurrentUser();
        if (currentUserId == null) {
            return ResponseVo.serviceFail("请先登录");
        }
        if (currentUserId.equals(userId)) {
            UserInfoModel user = userApi.updateUserInfoModel(userInfoModel);
            if (user != null) {
                return ResponseVo.success(user);
            }
        }
        return ResponseVo.serviceFail("更新用户:" + userId + " 失败");
    }


}
