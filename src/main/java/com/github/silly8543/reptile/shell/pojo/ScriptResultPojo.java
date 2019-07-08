package com.github.silly8543.reptile.shell.pojo;


/**
 * 脚本结果实体
 */
public class ScriptResultPojo<T> {


    private     boolean flag;


    private String error;

    /**
     * 执行数据
     */
    T data;

    public ScriptResultPojo() {
        flag = true;
    }


}
