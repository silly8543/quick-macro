package com.github.silly8543.reptile.instruction.pojo;

import com.github.silly8543.reptile.instruction.enums.InputTypeEnum;

/**
 * 输入
 * Created by silly on 2019/3/11 14:23
 */
public class InputPojo {
    /**
     * 输入类型
     */
    private InputTypeEnum type;

    /**
     * 输入key
     */
    private String key;
    /**
     * 输入值
     */
    private String value;


    public InputPojo() {

    }

    public static InputPojo valueOf(InputTypeEnum type, String key) {
        InputPojo pojo = new InputPojo();
        pojo.setType(type);
        pojo.setKey(key);
        return pojo;
    }

    @Override
    public String toString() {
        return type + " " + key + " " + value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public InputTypeEnum getType() {
        return type;
    }

    public void setType(InputTypeEnum type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
