package com.stylefeng.guns.rest.common;

/**
 * @author Seven.Lin
 * @date 2020/2/4 12:48
 */
public class CurrentUser {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void saveUserId(Integer userId) {
        threadLocal.set(userId);
    }

    public static Integer getCurrentUser() {
        return threadLocal.get();
    }

}
