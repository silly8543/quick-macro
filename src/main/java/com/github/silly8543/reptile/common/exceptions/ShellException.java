package com.github.silly8543.reptile.common.exceptions;

import com.github.silly8543.reptile.common.utils.StringUtils;

/**
 * shell异常
 *
 * @Author: silly
 * @Date: 2019/3/20 16:23
 * @Version 1.0
 * @Desc
 */
public class ShellException extends Exception {
    /**
     * 异常实体
     */
    Exception ex;


    public ShellException(String message) {
        super(message);
    }

    public static ShellException valueOf(Exception e, String message, String... params) {
        message = StringUtils.format(message, params);
        ShellException ex = new ShellException(message);
        ex.setEx(e);
        return ex;
    }

    public static ShellException valueOf(String message, String... params) {
        return valueOf(null, message, params);
    }

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }
}
