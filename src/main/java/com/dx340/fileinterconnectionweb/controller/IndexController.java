package com.dx340.fileinterconnectionweb.controller;

import com.dx340.fileinterconnectionweb.bean.ApiResult;
import com.dx340.fileinterconnectionweb.bean.LoginInfo;
import com.dx340.fileinterconnectionweb.bean.User;
import com.dx340.fileinterconnectionweb.service.IndexService;
import com.dx340.fileinterconnectionweb.util.ApiResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zsk-d
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IndexService indexService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult login(@RequestBody LoginInfo loginInfo) {
        boolean res = indexService.checkPassword(loginInfo);
        return res?ApiResultUtil.getOkResult():ApiResultUtil.getForbiddenResult("error");
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ApiResult userRegOrUpdate(@RequestBody User user) {
        if(user.getId() == null){
            indexService.addUser(user);
        }else{
            indexService.updateUser(user);
        }
        System.out.println("ok");
        return ApiResultUtil.getOkResult();
    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public ApiResult userDel(@RequestBody User user) {
        indexService.delUser(user);
        System.out.println("delete succeed！");
        return ApiResultUtil.getOkResult();
    }


}
