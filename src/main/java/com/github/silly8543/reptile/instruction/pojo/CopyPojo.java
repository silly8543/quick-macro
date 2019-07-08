package com.github.silly8543.reptile.instruction.pojo;

/**
 * 拷贝实体
 */
public class CopyPojo {

    /**
     * 占位符
     */
    private String placeholder;

    @Override
    public String toString() {
        return placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
