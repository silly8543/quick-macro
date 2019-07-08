package com.github.silly8543.reptile.shell.actuator;

import com.github.silly8543.reptile.ShellFactory;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.pojo.ScriptPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * shell执行器
 *
 * @Author: silly
 * @Date: 2019/6/20 16:34
 * @Version 1.0
 * @Desc
 */
public class ShellExecute {

    ShellPojo shellPojo;

    public ShellExecute() {

    }

    /**
     * 构造函数
     *
     * @param shellPojo 脚本实体
     */
    public ShellExecute(ShellPojo shellPojo) {
        this();
        this.shellPojo = shellPojo;
    }

    /**
     * 执行脚本
     *
     * @return
     */
    public ShellPojo execute() throws ShellException {
        ArrayList<ScriptPojo> scriptPojos = shellPojo.getScriptPojos();
        for (ScriptPojo scriptPojo : scriptPojos) {
            String cmd = scriptPojo.getCmd();
            IScript beanScript = ShellFactory.getContext().getScript(cmd);
            Map<String, String> result = beanScript.execute(ShellFactory.getContext().getRobot(), scriptPojo.getPojo(), shellPojo);
            if (result != null && result.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    shellPojo.addResults(entry.getKey(), entry.getValue());
                }
            }
        }
        return shellPojo;
    }
}
