package com.github.silly8543.reptile.instruction.enums;

/**
 * 输入类型枚举
 *
 * @Author: silly
 * @Date: 2019/3/20 15:52
 * @Version 1.0
 * @Desc
 */
public enum InputTypeEnum {
    /**
     * 拷贝方式输入
     */
    COPY("-c");


    String value;

    InputTypeEnum(String value) {
        this.value = value;
    }

    public static InputTypeEnum getTypeEnum(String value) {
        InputTypeEnum[] values = InputTypeEnum.values();
        for (InputTypeEnum typeEnum : values) {
            if (value.equalsIgnoreCase(typeEnum.getValue())) {
                return typeEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

