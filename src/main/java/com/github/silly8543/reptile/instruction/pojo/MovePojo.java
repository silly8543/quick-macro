package com.github.silly8543.reptile.instruction.pojo;

/**
 * 移动
 * Created by silly on 2019/3/11 11:09
 */
public class MovePojo {

    /**
     * x坐标
     */
    private int x;
    /**
     * y坐标
     */
    private int y;

    public MovePojo() {

    }

    public static MovePojo valueOf(int x, int y) {
        MovePojo pojo = new MovePojo();
        pojo.setX(x);
        pojo.setY(y);
        return pojo;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
