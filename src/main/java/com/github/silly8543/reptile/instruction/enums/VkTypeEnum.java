package com.github.silly8543.reptile.instruction.enums;

import java.awt.event.KeyEvent;

/**
 * 键盘值枚举
 *
 * @Author: silly
 * @Date: 2019/4/8 11:52
 * @Version 1.0
 * @Desc
 */
public enum VkTypeEnum {

    /**
     * 上
     */
    UP("UP", KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED, KeyEvent.CHAR_UNDEFINED),
    /**
     * 下
     */
    DOWN("DOWN", KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED, KeyEvent.CHAR_UNDEFINED),
    /**
     * 左
     */
    LEFT("LEFT", KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED, KeyEvent.CHAR_UNDEFINED),
    /**
     * 右
     */
    RIGHT("RIGHT", KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED, KeyEvent.CHAR_UNDEFINED);

    int code1;
    int code2;
    int code3;
    String key;

    VkTypeEnum(String key, int code1, int code2, int code3) {
        this.key = key;
        this.code1 = code1;
        this.code2 = code2;
        this.code3 = code3;
    }


    public static VkTypeEnum getEnum(String key) {
        VkTypeEnum[] values = VkTypeEnum.values();
        for (VkTypeEnum typeEnum : values) {
            if (key.equalsIgnoreCase(typeEnum.getKey())) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCode1() {
        return code1;
    }

    public void setCode1(int code1) {
        this.code1 = code1;
    }

    public int getCode2() {
        return code2;
    }

    public void setCode2(int code2) {
        this.code2 = code2;
    }

    public int getCode3() {
        return code3;
    }

    public void setCode3(int code3) {
        this.code3 = code3;
    }
}
