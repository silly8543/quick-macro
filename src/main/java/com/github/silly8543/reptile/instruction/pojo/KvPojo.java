package com.github.silly8543.reptile.instruction.pojo;

import com.github.silly8543.reptile.instruction.enums.VkTypeEnum;

/**
 * 键盘实体
 *
 * @Author: silly
 * @Date: 2019/4/8 11:51
 * @Version 1.0
 * @Desc
 */
public class KvPojo {

    VkTypeEnum type;

    @Override
    public String toString() {
        return type.getKey().toLowerCase();
    }

    public VkTypeEnum getType() {
        return type;
    }

    public void setType(VkTypeEnum type) {
        this.type = type;
    }
}
