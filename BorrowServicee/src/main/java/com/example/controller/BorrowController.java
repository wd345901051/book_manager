package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.UserBorrowDetail;
import com.example.service.BorrowService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class BorrowController {

    @Resource
    BorrowService service;


    @RequestMapping("borrow/{uid}")
    UserBorrowDetail findUserBorrow(@PathVariable("uid") int uid){
        return service.getUserBorrowDetailByUid(uid);
    }

    // sentinel限流页面 在配置文件中配置
    @RequestMapping("error")
    JSONObject error(){
        JSONObject object = new JSONObject();
        object.put("code",403);
        object.put("success",false);
        object.put("massage","请求过快");
        return object;
    }

}
