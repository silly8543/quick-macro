package com.github.silly8543.reptile.shell.actuator;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.shell.actuator.pojo.TemplatePojo;
import com.github.silly8543.reptile.shell.pojo.logic.BlockPojo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * 模板解析器
 *
 * @Author: silly
 * @Date: 2019/6/18 16:22
 * @Version 1.0
 * @Desc
 */
public class TemplateParser implements IParser<TemplatePojo> {

    /**
     * 脚本行数据
     */
    @Getter
    @Setter
    ArrayList<String[]> datas;

    @Getter
    @Setter
    TemplatePojo templatePojo;

    /**
     * 构造函数
     */
    public TemplateParser() {
        templatePojo = new TemplatePojo();
    }


    /**
     * 构造函数
     *
     * @param name  模板名称
     * @param datas 模板脚本行
     */
    public TemplateParser(String name, ArrayList<String[]> datas) {
        this();
        this.datas = datas;
        templatePojo.setName(name);
    }

    /**
     * 构造函数
     *
     * @param name   模板名称
     * @param datas  模板脚本行
     * @param params 脚本输入参数
     */
    public TemplateParser(String name, ArrayList<String[]> datas, ArrayList<String> params) {
        this(name, datas);
        templatePojo.setParams(params);
    }

    @Override
    public TemplatePojo parse() throws ShellException {
        for (int i = 0; i < datas.size(); i++) {
            String[] script = datas.get(i);
            String cmd = script[0];
            String param = script[1];
            switch (cmd) {
                //block
                case ShellConstant.LOGIC.BLOCK:
                    BlockPojo blockPojo = new BlockPojo();
                    blockPojo.setName(param);
                    i = disposeBlock(blockPojo, datas, i);
                    templatePojo.addScript(new String[]{ShellConstant.LOGIC.BLOCK, blockPojo.getName()});
                    templatePojo.addBlock(blockPojo);
                    break;
                default:
                    templatePojo.addScript(script);
            }
        }
        return templatePojo;
    }

}
