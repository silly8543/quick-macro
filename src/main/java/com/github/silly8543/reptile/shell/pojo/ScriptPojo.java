package com.github.silly8543.reptile.shell.pojo;

import com.github.silly8543.reptile.shell.enums.ScriptTypeEnum;

/**
 * 脚本实体对象
 *
 * @Author: silly
 * @Date: 2019/3/21 12:18
 * @Version 1.0
 * @Desc
 */
public class ScriptPojo {

    /**
     * 类型
     */
    private ScriptTypeEnum type;

    //cmd相关参数
    /**
     * 名称
     */
    private String cmd;
    /**
     * 参数对象
     */
    private Object pojo;

    public ScriptPojo() {

    }

    @Override
    public String toString() {
        return cmd + " " + (pojo != null ? pojo.toString() : "");
    }

    public static ScriptPojo valueOf(String cmd, Object pojo) {
        ScriptPojo scriptPojo = new ScriptPojo();
        scriptPojo.setCmd(cmd);
        scriptPojo.setPojo(pojo);
        return scriptPojo;
    }

    public Object getPojo() {
        return pojo;
    }

    public void setPojo(Object pojo) {
        this.pojo = pojo;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setType(ScriptTypeEnum type) {
        this.type = type;
    }

    public ScriptTypeEnum getType() {
        return type;
    }
}
