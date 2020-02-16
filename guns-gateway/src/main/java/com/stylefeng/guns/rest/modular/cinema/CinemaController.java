package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import guns.api.cinema.ICinemaService;
import guns.api.cinema.vo.CinemaQueryVo;
import guns.api.cinema.vo.CinemaVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seven.Lin
 * @date 2020/2/16 17:49
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController {
    @Reference(interfaceClass = ICinemaService.class, check = false)
    private ICinemaService cinemaService;

    @GetMapping("/getCinemas")
    public ResponseVo getCinemas(CinemaQueryVo cinemaQueryVo) {
        Page<CinemaVo> cinemaVoPage = cinemaService.getCinemas(cinemaQueryVo);
        return ResponseVo.success(cinemaVoPage);
    }

    @GetMapping("/getCondition")
    public ResponseVo getCondition(CinemaQueryVo cinemaQueryVo) {
        return null;
    }

    @GetMapping("/getFields")
    public ResponseVo getFields(Integer cinemaId) {
        return null;
    }

    @PostMapping("/getFieldInfo")
    public ResponseVo getFieldInfo(Integer cinema, Integer fieldId) {
        return null;
    }

}
