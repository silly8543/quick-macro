package com.github.silly8543.reptile.instruction.enums;

/**
 * 清空类型枚举
 *
 * @Author: silly
 * @Date: 2019/3/20 16:10
 * @Version 1.0
 * @Desc
 */
public enum ClearTypeEnum {
    INPUT("-i"),
    ALL("-a");

    String value;

    public static ClearTypeEnum getTypeEnum(String value) {
        ClearTypeEnum[] values = ClearTypeEnum.values();
        for (ClearTypeEnum typeEnum : values) {
            if (value.equalsIgnoreCase(typeEnum.getValue())) {
                return typeEnum;
            }
        }
        return null;
    }

    ClearTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
