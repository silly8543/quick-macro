package com.github.silly8543.reptile.shell.pojo.logic;

import com.github.silly8543.reptile.common.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * block
 *
 * @Author: silly
 * @Date: 2019/6/18 12:08
 * @Version 1.0
 * @Desc
 */
public class BlockPojo {
    /**
     * 块名
     */
    @Setter
    @Getter
    private String name;
    /**
     * 脚本行列表
     */
    @Setter
    @Getter
    private List<String[]> scripts;
    /**
     * 字段列表
     */
    @Setter
    @Getter
    private Map<String, FieldPojo> fields;

    public BlockPojo() {
        scripts = new ArrayList<>();
        fields = new HashMap<>();
    }

    public BlockPojo(String name) {
        this();
        this.name = name;
    }

    /**
     * 添加脚本
     *
     * @param script 脚本行
     * @return
     */
    public void addScript(String[] script) {
        scripts.add(script);
    }

    /**
     * 添加字段
     *
     * @param fieldPojo 字段实体
     */
    public void addField(FieldPojo fieldPojo) {
        fields.put(fieldPojo.getName(), fieldPojo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====block_name=====");
        sb.append(System.lineSeparator());
        sb.append(name != null ? name : "");
        sb.append(System.lineSeparator());
        sb.append("=====block_scripts=====");
        sb.append(System.lineSeparator());
        sb.append(StringUtils.print(scripts));
        sb.append("=====block_field=====");
        sb.append(System.lineSeparator());
        if (fields != null && fields.size() > 0) {
            Iterator<Map.Entry<String, FieldPojo>> iterator = fields.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, FieldPojo> entry = iterator.next();
                sb.append(entry.getValue().toString());
            }
        }
        return sb.toString();
    }
}
