package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

/**
 * @author Seven.Lin
 * @date 2020/2/4 12:30
 */
@Data
public class ResponseVo<M> {
    private int status;
    private String msg;
    private M data;
    private String imgPre;
    private Integer nowPage;
    private Integer totalPage;

    private static final String IMG_PRE = "https://www.meetingshop.cn";

    private ResponseVo() {
    }

    public static <M> ResponseVo success(M data) {
        ResponseVo response = new ResponseVo();
        response.setStatus(0);
        response.setData(data);
        return response;
    }

    public static <M> ResponseVo success(M data, String msg) {
        ResponseVo response = new ResponseVo();
        response.setStatus(0);
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static <M> ResponseVo success(M data, boolean hasImgPre) {
        ResponseVo response = new ResponseVo();
        response.setStatus(0);
        response.setData(data);
        if (hasImgPre) {
            response.setImgPre(IMG_PRE);
        }
        return response;
    }

    public static <M> ResponseVo success(M data, boolean hasImgPre, Integer nowPage, Integer totalPage) {
        ResponseVo response = new ResponseVo();
        response.setStatus(0);
        response.setData(data);
        response.setNowPage(nowPage);
        response.setTotalPage(totalPage);
        if (hasImgPre) {
            response.setImgPre(IMG_PRE);
        }
        return response;
    }

    public static <M> ResponseVo serviceFail(String msg) {
        ResponseVo response = new ResponseVo();
        response.setStatus(-1);
        response.setMsg(msg);
        return response;
    }

    public static <M> ResponseVo appFail(String msg) {
        ResponseVo response = new ResponseVo();
        response.setStatus(999);
        response.setMsg(msg);
        return response;
    }

}
