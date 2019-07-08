package com.github.silly8543.reptile.shell.pojo.logic;

import com.github.silly8543.reptile.common.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * 字段实体
 *
 * @Author: silly
 * @Date: 2019/6/18 17:05
 * @Version 1.0
 * @Desc
 */
public class FieldPojo {

    /**
     * 字段名
     */
    @Getter
    @Setter
    private String name;
    /**
     * 脚本行列表
     */
    @Getter
    @Setter
    private ArrayList<String[]> scripts;

    public FieldPojo() {
        this.scripts = new ArrayList<>();
    }

    public FieldPojo(String name) {
        this();
        this.name = name;
    }

    /**
     * 添加脚本行
     *
     * @param script
     */
    public void addScript(String[] script) {
        scripts.add(script);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====field_name=====");
        sb.append(System.lineSeparator());
        sb.append(name);
        sb.append(System.lineSeparator());
        sb.append("=====field_scripts=====");
        sb.append(System.lineSeparator());
        sb.append(StringUtils.print(scripts));
        return sb.toString();
    }
}
