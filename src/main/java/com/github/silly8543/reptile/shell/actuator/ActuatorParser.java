package com.github.silly8543.reptile.shell.actuator;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.utils.ShellUtils;
import com.github.silly8543.reptile.common.utils.StringUtils;
import com.github.silly8543.reptile.shell.actuator.pojo.ActuatorParserPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.TemplatePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 脚本解析器
 * Created by silly on 2019/3/11 16:50
 */
public class ActuatorParser {

    /**
     * 脚本数据
     */
    private List<String> data;
    /**
     * 输入参数
     */
    private ArrayList<String> params;

    private ActuatorParserPojo parserPojo;


    /**
     * 构造函数
     */
    public ActuatorParser() {
        this.parserPojo = new ActuatorParserPojo();
        this.params = new ArrayList<>();
    }

    /**
     * 构造函数
     *
     * @param data 脚本数据
     */
    public ActuatorParser(List<String> data) {
        this();
        this.data = data;
    }

    /**
     * 解析
     *
     * @return
     */
    public ActuatorParserPojo execute() throws ShellException {
        //对传入的数据预处理
        ArrayList<String[]> newData = disposeData();
        checkHead();
        //选择解析器
        switch (parserPojo.getType()) {
            case ShellConstant.MARK_TYPE.TEMPLATE:
                TemplateParser templetParser = new TemplateParser(parserPojo.getName(), newData, params);
                TemplatePojo templatePojo = templetParser.parse();
                parserPojo.setTemplatePojo(templatePojo);
                break;
            default:
                ShellParser shellParser = new ShellParser(parserPojo.getName(), newData, params);
                ShellPojo shellPojo = shellParser.parse();
                parserPojo.setShellPojo(shellPojo);
                break;
        }

        return parserPojo;
    }


    /**
     * 检查头部信息
     */
    private void checkHead() throws ShellException {
        if (StringUtils.isBlank(parserPojo.getName())) {
            throw ShellException.valueOf("shell scripts name is empty");
        }

    }

    /**
     * 对传入的数据先预处理
     *
     * @return
     */
    private ArrayList<String[]> disposeData() throws ShellException {
        ArrayList<String[]> newData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            // 空行跳过
            if (StringUtils.isBlank(line)) {
                continue;
            }
            //注释跳过
            if (line.startsWith(ShellConstant.MARK.ANNOTATE.getValue())) {
                continue;
            }
            String[] strs = StringUtils.spilt(line, ShellUtils.SCRIPT_SEPARATOR);
            if (strs.length < 2) {
                throw ShellException.valueOf("scripts error,{1}", line);
            }
            strs[0] = strs[0].toUpperCase();
            String value = strs[1];
            String cmd = strs[0];
            ShellConstant.MARK cmdMark = ShellUtils.getMark(cmd);
            if (cmdMark == null) {
                newData.add(strs);
                continue;
            }
            switch (cmdMark) {
                case NAME:
                    parserPojo.setName(value);
                    break;
                case VERSION:
                    break;
                case DESC:
                    break;
                case TYPE:
                    parserPojo.setType(getMarkType(value));
                    break;
                case PARAMS:
                    disposeMarkParams(line, strs);
                    break;

            }
        }
        return newData;
    }

    /**
     * 处理标记mark params 参数
     *
     * @param scripts 脚本行
     * @param strs    分隔后的cmd
     */
    private void disposeMarkParams(String scripts, String[] strs) throws ShellException {
        for (int i = 1; i < strs.length; i++) {
            String paramValue = strs[i];
            if (!paramValue.startsWith(ShellConstant.MARK.PLACEHOLDER_INPUT.getValue())) {
                throw ShellException.valueOf("shell params is error,scripts={1},param={2}", scripts, paramValue);
            }
            params.add(paramValue);
        }
    }

    /**
     * 获取标记类型
     *
     * @param value
     * @return
     */
    private String getMarkType(String value) {
        if (!StringUtils.isBlank(value)) {
            value = value.toUpperCase();
        } else {
            value = ShellConstant.MARK_TYPE.SHELL;
        }
        switch (value) {
            case ShellConstant.MARK_TYPE.SHELL:
                value = ShellConstant.MARK_TYPE.SHELL;
                break;
            case ShellConstant.MARK_TYPE.TEMPLATE:
                value = ShellConstant.MARK_TYPE.TEMPLATE;
                break;
        }
        return value;
    }
}
