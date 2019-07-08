package com.github.silly8543.reptile.shell.processor.cmd;


import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.ClearTypeEnum;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.ClearPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 清空
 * clear -a
 * clear -i 100
 */
@Script(ShellConstant.CMD.CLEAR)
public class ClearScript implements IScript<ClearPojo> {
    @Override
    public ClearPojo parser(String script, List<String> params) throws ShellException {
        String param = params.get(0);
        int count = 0;
        ClearTypeEnum typeEnum = ClearTypeEnum.getTypeEnum(param);
        if (typeEnum == null) {
            throw exParamTypeNotSupported(ShellConstant.CMD.CLEAR, script, params);
        }
        if (typeEnum == ClearTypeEnum.INPUT) {
            try {
                String value = params.get(1);
                count = Integer.parseInt(value);
            } catch (Exception ex) {
                throw ShellException.valueOf(ex,"{1}  -i should be followed by the number of deletions,script:{2}", ShellConstant.CMD.CLEAR, getScript(script, params));
            }
        }
        ClearPojo pojo = new ClearPojo();
        pojo.setType(typeEnum);
        pojo.setCount(count);
        return pojo;
    }

    @Override
    public Map<String, String> execute(Robot robot, ClearPojo clearPojo, ShellPojo shellPojo) throws ShellException {
        return executeCmd(CmdTypeEnum.CLEAR, robot, clearPojo);
    }


}
