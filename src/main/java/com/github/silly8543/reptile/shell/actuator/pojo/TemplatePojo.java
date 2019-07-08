package com.github.silly8543.reptile.shell.actuator.pojo;

import com.github.silly8543.reptile.common.utils.StringUtils;
import com.github.silly8543.reptile.shell.pojo.logic.BlockPojo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 模板实体
 *
 * @Author: silly
 * @Date: 2019/6/18 16:24
 * @Version 1.0
 * @Desc
 */
public class TemplatePojo {
    /**
     * 名称
     */
    @Getter
    @Setter
    String name;
    /**
     * 参数列表
     */
    @Getter
    @Setter
    ArrayList<String> params;
    /**
     * 脚本行
     */
    @Getter
    @Setter
    ArrayList<String[]> scripts;
    /**
     * 代码块
     */
    @Getter
    @Setter
    Map<String, BlockPojo> blocks;

    public TemplatePojo() {
        scripts = new ArrayList<>();
        blocks = new HashMap<>();

    }

    public TemplatePojo(String name) {
        this();
        this.name = name;
    }

    /**
     * 添加脚本行
     *
     * @param script 脚本行
     */
    public void addScript(String[] script) {
        scripts.add(script);
    }

    /**
     * 添加块
     *
     * @param blockPojo 块实体
     */
    public void addBlock(BlockPojo blockPojo) {
        blocks.put(blockPojo.getName(), blockPojo);
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("=====name=====");
        sb.append(System.lineSeparator());
        sb.append(name != null ? name : "");
        sb.append(System.lineSeparator());
        sb.append("=====scripts=====");
        sb.append(System.lineSeparator());
        sb.append(StringUtils.print(scripts));
        sb.append("=====params=====");
        sb.append(System.lineSeparator());
        sb.append(StringUtils.print(params));
        sb.append("=====blocks=====");
        sb.append(System.lineSeparator());
        if (blocks != null && blocks.size() > 0) {
            Iterator<Map.Entry<String, BlockPojo>> iterator = blocks.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, BlockPojo> entry = iterator.next();
                sb.append(entry.getValue().toString());
            }
        }
        return sb.toString();
    }

}
