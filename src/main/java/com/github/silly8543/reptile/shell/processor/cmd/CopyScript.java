package com.github.silly8543.reptile.shell.processor.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.CopyPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 复制
 * copy &1
 */
@Script(ShellConstant.CMD.COPY)
public class CopyScript implements IScript<CopyPojo> {
    @Override
    public CopyPojo parser(String script, List<String> params) throws ShellException {
        String param = params.get(0);
        if (!param.startsWith(ShellConstant.MARK.PLACEHOLDER_OUTPUT.getValue())) {
            throw ShellException.valueOf("{1}command parameters only support output parameters,script:{2}", ShellConstant.CMD.COPY, getScript(script, params));
        }
        CopyPojo pojo = new CopyPojo();
        pojo.setPlaceholder(param);
        return pojo;
    }

    @Override
    public Map<String, String> execute(Robot robot, CopyPojo copyPojo, ShellPojo shellPojo) throws ShellException {
        return executeCmd(CmdTypeEnum.COPY, robot, copyPojo);
    }
}
