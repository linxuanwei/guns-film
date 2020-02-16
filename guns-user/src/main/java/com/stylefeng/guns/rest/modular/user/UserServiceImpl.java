package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import guns.api.user.IUserApi;
import guns.api.user.vo.UserInfoModel;
import guns.api.user.vo.UserModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Seven.Lin
 * @date 2020/1/22 18:01
 */
@Component
@Service(interfaceClass = IUserApi.class)
public class UserServiceImpl implements IUserApi {
    @Resource
    private MoocUserTMapper moocUserTMapper;

    @Override
    public int login(String name, String password) {
        System.out.println("login username:" + name + ", password:" + password);
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(name);
        MoocUserT result = moocUserTMapper.selectOne(moocUserT);
        if (result != null && result.getUserPwd().equals(MD5Util.encrypt(password))) {
            return result.getUuid();
        }
        return 0;
    }

    @Override
    public boolean registry(UserModel userModal) {
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModal.getUsername());
        moocUserT.setUserPwd(MD5Util.encrypt(userModal.getPassword()));
        moocUserT.setEmail(userModal.getEmail());
        moocUserT.setAddress(userModal.getAddress());
        moocUserT.setUserPhone(userModal.getPhone());

        int insertNum = moocUserTMapper.insert(moocUserT);
        return insertNum > 0;
    }

    @Override
    public boolean checkUsername(String name) {

        EntityWrapper<MoocUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name", name);

        Integer result = moocUserTMapper.selectCount(entityWrapper);
        return result == null || result <= 0;

    }

    private UserInfoModel do2UserInfo(MoocUserT moocUserT) {
        if (moocUserT == null) {
            return null;
        }
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUuid(moocUserT.getUuid());
        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(moocUserT.getLifeState());
        userInfoModel.setHeadUrl(moocUserT.getHeadUrl());
        userInfoModel.setEmail(moocUserT.getBirthday());
        userInfoModel.setBiography(moocUserT.getBiography());
        userInfoModel.setBeginTime(moocUserT.getBeginTime());
        userInfoModel.setAddress(moocUserT.getAddress());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        return userInfoModel;
    }

    private MoocUserT userInfoToDo(UserInfoModel userInfoModel) {
        if (userInfoModel == null) {
            return null;
        }
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUuid(userInfoModel.getUuid());
        moocUserT.setUserName(userInfoModel.getUsername());
        moocUserT.setUpdateTime(userInfoModel.getUpdateTime());
        moocUserT.setUserSex(userInfoModel.getSex());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(userInfoModel.getLifeState());
        moocUserT.setHeadUrl(userInfoModel.getHeadUrl());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setBeginTime(userInfoModel.getBeginTime());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setBirthday(userInfoModel.getBirthday());
        return moocUserT;
    }

    @Override
    public UserInfoModel getUserInfoModel(int userId) {
        MoocUserT moocUserT = moocUserTMapper.selectById(userId);
        return this.do2UserInfo(moocUserT);
    }

    @Override
    public UserInfoModel updateUserInfoModel(UserInfoModel userInfoModel) {
        MoocUserT moocUserT = this.userInfoToDo(userInfoModel);
        Integer updateNum = moocUserTMapper.updateById(moocUserT);
        if (updateNum > 0) {
            return this.getUserInfoModel(userInfoModel.getUuid());
        }
        return userInfoModel;
    }
}
