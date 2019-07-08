package com.github.silly8543.reptile.shell.processor;

import com.github.silly8543.reptile.ShellFactory;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.cmd.ICmd;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.pojo.ScriptPojo;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 处理器接口
 * Created by silly on 2019/3/11 17:23
 */
public interface IScript<T> {


    /**
     * 解析脚本
     *
     * @param script
     * @param params
     * @return
     * @throws ShellException
     */
    T parser(String script, List<String> params) throws ShellException;


    /**
     * 执行
     *
     * @param robot     键盘鼠标操作类
     * @param t         脚本参数实体
     * @param shellPojo 脚本
     * @return
     */
    Map<String, String> execute(Robot robot, T t, ShellPojo shellPojo) throws ShellException;


    /**
     * 执行cmd
     *
     * @param typeEnum 执行类枚举
     * @param robot    键盘鼠标操作类
     * @param obj      命令参数实体
     * @return
     */
    default Map<String, String> executeCmd(CmdTypeEnum typeEnum, Robot robot, Object obj) throws ShellException {
        ICmd cmd;
        try {
            cmd = (ICmd) typeEnum.getClazz().newInstance();
        } catch (Exception ex) {
            throw ShellException.valueOf(ex, "create cmd entity exception,name={1},clazz={2}", typeEnum.getName(), typeEnum.getClazz().getName());
        }
        return cmd.execute(robot, obj);
    }


    /**
     * 执行script
     *
     * @param robot
     * @param scriptPojo
     * @param shellPojo
     * @return
     * @throws ShellException
     */
    default Map<String, String> executeScript(Robot robot, ScriptPojo scriptPojo, ShellPojo shellPojo) throws ShellException {
        String cmd = scriptPojo.getCmd();
        IScript beanScript = ShellFactory.getContext().getScript(cmd);
        return beanScript.execute(ShellFactory.getContext().getRobot(), scriptPojo.getPojo(), shellPojo);
    }

    /**
     * 获取脚本字符串
     *
     * @param script
     * @param params
     * @return
     */
    default String getScript(String script, List<String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(script);
        sb.append(" ");
        if (params != null && params.size() > 0) {
            for (String param : params) {
                sb.append(param);
                sb.append(" ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * 提示信息：参数类型不支持
     *
     * @param cmd
     * @param script
     * @param params
     * @return
     */
    default ShellException exParamTypeNotSupported(String cmd, String script, List<String> params) {
        return ShellException.valueOf("{1} param type not supported,script:{2}", cmd, getScript(script, params));
    }


}
