package com.dx340.fileinterconnectionweb.util;


import com.dx340.fileinterconnectionweb.bean.ApiResult;

/**
 * @author Zsk-d
 */
public class ApiResultUtil {
    /**
     * return ok empty res obj
     *
     * @return
     */
    public static ApiResult getOkResult() {
        return new ApiResult(200, null, null);
    }

    public static ApiResult getOkResult(Object res) {
        return new ApiResult(200, null, res);
    }

    /**
     * return forbidden res obj
     *
     * @return
     */
    public static ApiResult getForbiddenResult() {
        return new ApiResult(403, null, null);
    }

    public static ApiResult getForbiddenResult(String message) {
        return new ApiResult(403, message, null);
    }

    public static ApiResult getErrorResult(String message) {
        return new ApiResult(500, message, null);
    }

    public static ApiResult getErrorResult() {
        return new ApiResult(500, null, null);
    }

    public static ApiResult getNotFoundResult(String message) {
        return new ApiResult(404, message, null);
    }

    public static ApiResult getNotFoundResult() {
        return new ApiResult(404, null, null);
    }
}
