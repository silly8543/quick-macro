package com.github.silly8543.reptile.instruction.pojo;

import com.github.silly8543.reptile.instruction.enums.ClearTypeEnum;

/**
 * 清空输入框
 *
 * @Author: silly
 * @Date: 2019/3/20 15:42
 * @Version 1.0
 * @Desc
 */
public class ClearPojo {

    private ClearTypeEnum type;

    /**
     * 需要清除的个数
     */
    private int count;

    @Override
    public String toString() {
        String str = "";
        switch (type) {
            case ALL:
                str = "-a";
                break;
            case INPUT:
                str = "-i " + count;
                break;
        }
        return str;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ClearTypeEnum getType() {
        return type;
    }

    public void setType(ClearTypeEnum type) {
        this.type = type;
    }
}
