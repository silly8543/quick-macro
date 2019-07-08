package com.github.silly8543.reptile.common.utils;

import com.github.silly8543.reptile.common.ShellConstant;

/**
 * 脚本工具类
 *
 * @Author: silly
 * @Date: 2019/6/20 13:44
 * @Version 1.0
 * @Desc
 */
public class ShellUtils {

    /**
     * 脚本分隔符
     */
    public final static String SCRIPT_SEPARATOR = "\\s+";

    /**
     * 是否是标记符
     *
     * @param cmd
     * @return
     */
    public static boolean isMarkTag(String cmd) {
        ShellConstant.MARK[] values = ShellConstant.MARK.values();
        for (ShellConstant.MARK typeEnum : values) {
            if (cmd.startsWith(typeEnum.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取标记符
     *
     * @param cmd
     * @return
     */
    public static ShellConstant.MARK getMark(String cmd) {
        ShellConstant.MARK[] values = ShellConstant.MARK.values();
        for (ShellConstant.MARK typeEnum : values) {
            if (cmd.equals(typeEnum.getValue())) {
                return typeEnum;
            }
        }
        return null;
    }
}
