package com.github.silly8543.reptile.instruction.pojo;

/**
 * 等待
 * Created by silly on 2019/3/12 13:34
 */
public class WaitPojo {

    /**
     * 时间：s
     */
    private int time;

    public WaitPojo() {
    }

    public static WaitPojo valueOf(int time) {
        WaitPojo pojo = new WaitPojo();
        pojo.setTime(time);
        return pojo;
    }

    @Override
    public String toString() {
        return String.valueOf(time);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
