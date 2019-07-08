package com.github.silly8543.reptile.shell.actuator;

import com.github.silly8543.reptile.ShellFactory;
import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.utils.ShellUtils;
import com.github.silly8543.reptile.common.utils.StringUtils;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.enums.ScriptTypeEnum;
import com.github.silly8543.reptile.shell.pojo.ScriptPojo;
import com.github.silly8543.reptile.shell.processor.IScript;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * shell解析器
 *
 * @Author: silly
 * @Date: 2019/6/19 10:23
 * @Version 1.0
 * @Desc
 */
public class ShellParser implements IParser<ShellPojo> {
    /**
     * 脚本行数据
     */
    @Getter
    @Setter
    ArrayList<String[]> datas;

    /**
     * 参数
     */
    @Getter
    @Setter
    ArrayList<String> params;

    ShellPojo shellPojo;


    /**
     * 构造函数
     */
    public ShellParser() {
        shellPojo = new ShellPojo();
    }

    /**
     * 构造函数
     *
     * @param name  shell名称
     * @param datas 模板脚本行
     */
    public ShellParser(String name, ArrayList<String[]> datas) {
        this();
        shellPojo.setName(name);
        this.datas = datas;
    }

    /**
     * 构造函数
     *
     * @param name   模板名称
     * @param datas  模板脚本行
     * @param params 参数列表
     */
    public ShellParser(String name, ArrayList<String[]> datas, ArrayList<String> params) {
        this(name, datas);
        this.params = params;
    }

    @Override
    public ShellPojo parse() throws ShellException {
        if (datas == null || datas.size() <= 0) {
            throw ShellException.valueOf("datas is empty");
        }
        //遍历指令代码
        for (String[] strs : datas) {
            if (strs.length < 2) {
                throw ShellException.valueOf("The number of executions is less than 2,scripts:{1}", StringUtils.join(" ", strs));
            }
            if (ShellUtils.isMarkTag(strs[0])) {
                //标记字符，跳过
                continue;
            }
            ScriptPojo scriptPojo = disposeScript(strs);
            shellPojo.addScriptPojo(scriptPojo);
        }
        addParams();
        return shellPojo;
    }


    /**
     * 添加参数
     */
    private void addParams() {
        if (params == null || params.size() <= 0) {
            return;
        }
        for (String param : params) {
            shellPojo.addParams(param, param);
        }
    }

    /**
     * 处理脚本
     *
     * @param strs
     * @return
     */
    private ScriptPojo disposeScript(String[] strs) throws ShellException {
        ScriptPojo scriptPojo = new ScriptPojo();
        String cmd = strs[0].toUpperCase();
        scriptPojo.setCmd(cmd);
        List<String> params = Arrays.asList(strs).subList(1, strs.length);
        if (cmd.startsWith(ShellConstant.LOGIC.FLAG)) {
            scriptPojo.setType(ScriptTypeEnum.LOGIC);
        } else {
            scriptPojo.setType(ScriptTypeEnum.CMD);
        }
        IScript bean = ShellFactory.getContext().getScript(cmd);
        Object parserPojo = bean.parser(StringUtils.join(" ", strs), params);
        scriptPojo.setPojo(parserPojo);
        //判断是否有占位符
        for (String param : params) {
            //输入参数
            if (param.startsWith(ShellConstant.MARK.PLACEHOLDER_INPUT.getValue())) {
                shellPojo.addParams(param, null);
            }
            //输出参数
            if (param.startsWith(ShellConstant.MARK.PLACEHOLDER_OUTPUT.getValue())) {
                shellPojo.addResults(param, null);
            }
        }
        return scriptPojo;
    }


}
