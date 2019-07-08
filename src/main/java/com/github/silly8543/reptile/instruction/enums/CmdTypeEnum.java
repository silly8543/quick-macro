package com.github.silly8543.reptile.instruction.enums;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.instruction.cmd.*;

/**
 * cmd类型枚举
 *
 * @Author: silly
 * @Date: 2019/3/25 16:55
 * @Version 1.0
 * @Desc
 */
public enum CmdTypeEnum {

    /**
     * 清空
     */
    CLEAR(ShellConstant.CMD.CLEAR, ClearCmd.class),
    /**
     * 点击
     */
    CLICK(ShellConstant.CMD.CLICK, ClickCmd.class),
    /**
     * 复制
     */
    COPY(ShellConstant.CMD.COPY, CopyCmd.class),
    /**
     * 输入
     */
    INPUT(ShellConstant.CMD.INPUT, InputCmd.class),
    /**
     * 移动
     */
    MOVE(ShellConstant.CMD.MOVE, MoveCmd.class),
    /**
     * 等待
     */
    WAIT(ShellConstant.CMD.WAIT, WaitCmd.class);

    String name;
    Class<?> clazz;

    CmdTypeEnum(String name, Class<? extends ICmd> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public static CmdTypeEnum getTypeEnum(String value) {
        CmdTypeEnum[] values = CmdTypeEnum.values();
        for (CmdTypeEnum typeEnum : values) {
            if (value.equalsIgnoreCase(typeEnum.getName())) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}


