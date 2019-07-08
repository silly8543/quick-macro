package com.github.silly8543.reptile.shell.processor.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.WaitPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 等待
 * Created by silly on 2019/3/12 13:37
 */
@Script(ShellConstant.CMD.WAIT)
public class WaitScript implements IScript<WaitPojo> {

    @Override
    public WaitPojo parser(String script, List<String> params) throws ShellException {
        WaitPojo pojo = new WaitPojo();
        try {
            pojo.setTime(Integer.parseInt(params.get(0)));
        } catch (Exception ex) {
            throw ShellException.valueOf("{1} time parameters cannot be converted,script:{2}", ShellConstant.CMD.WAIT, getScript(script, params));
        }
        return pojo;
    }

    @Override
    public Map<String, String> execute(Robot robot, WaitPojo waitPojo, ShellPojo shellPojo) throws ShellException {
        return executeCmd(CmdTypeEnum.WAIT, robot, waitPojo);
    }

}
