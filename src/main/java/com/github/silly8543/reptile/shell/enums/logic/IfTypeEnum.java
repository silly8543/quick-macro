package com.github.silly8543.reptile.shell.enums.logic;

/**
 * @Author: silly
 * @Date: 2019/3/25 16:13
 * @Version 1.0
 * @Desc if类型枚举
 */
public enum IfTypeEnum {

    /**
     * 空判断
     */
    EMPTY("EMPTY");

    String value;

    public static IfTypeEnum getTypeEnum(String value) {
        IfTypeEnum[] values = IfTypeEnum.values();
        for (IfTypeEnum typeEnum : values) {
            if (value.equalsIgnoreCase(typeEnum.getValue())) {
                return typeEnum;
            }
        }
        return null;
    }

    IfTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
