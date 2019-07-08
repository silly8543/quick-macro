package com.github.silly8543.reptile.instruction.pojo;


import com.github.silly8543.reptile.instruction.enums.ClickTypeEnum;

/**
 * 点击
 * Created by silly on 2019/3/11 10:35
 */
public class ClickPojo {

    /**
     * 鼠标点击key
     *
     * @see ClickTypeEnum
     */
    private ClickTypeEnum type;

    /**
     * x坐标
     */
    int x;
    /**
     * y坐标
     */
    int y;


    public ClickPojo() {
    }

    public static ClickPojo valueOf(ClickTypeEnum type) {
        ClickPojo pojo = new ClickPojo();
        pojo.setType(type);
        return pojo;
    }

    @Override
    public String toString() {
        return type.getValue() + " " + x + " " + y;
    }

    public ClickTypeEnum getType() {
        return type;
    }

    public void setType(ClickTypeEnum type) {
        this.type = type;
    }


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
