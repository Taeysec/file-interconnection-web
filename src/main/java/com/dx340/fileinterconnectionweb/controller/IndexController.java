package com.dx340.fileinterconnectionweb.controller;

import com.dx340.fileinterconnectionweb.bean.ApiResult;
import com.dx340.fileinterconnectionweb.bean.LoginInfo;
import com.dx340.fileinterconnectionweb.util.ApiResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zsk-d
 */
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult login(@RequestBody LoginInfo loginInfo) {
        // todo :check password
        System.out.println(loginInfo);
        return ApiResultUtil.getOkResult();
    }
}
