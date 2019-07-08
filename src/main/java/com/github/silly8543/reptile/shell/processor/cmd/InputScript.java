package com.github.silly8543.reptile.shell.processor.cmd;


import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.instruction.enums.InputTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.InputPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 输入
 * input -c $1
 * input -c 测试
 */
@Script(ShellConstant.CMD.INPUT)
public class InputScript implements IScript<InputPojo> {

    @Override
    public InputPojo parser(String script, List<String> params) throws ShellException {
        String type = params.get(0);
        InputTypeEnum typeEnum = InputTypeEnum.getTypeEnum(type);
        if (typeEnum == null) {
            throw exParamTypeNotSupported(ShellConstant.CMD.INPUT, script, params);
        }
        if (params.size() != 2) {
            throw ShellException.valueOf("{1} parameter size is not equal 2,script:{2}", ShellConstant.CMD.INPUT, getScript(script, params));
        }
        InputPojo pojo = new InputPojo();
        pojo.setType(typeEnum);
        pojo.setKey(params.get(1));
        return pojo;
    }

    @Override
    public Map<String, String> execute(Robot robot, InputPojo inputPojo, ShellPojo shellPojo) throws ShellException {
        String key = inputPojo.getKey();
        if (key.startsWith(ShellConstant.MARK.PLACEHOLDER_INPUT.getValue())) {
            String value = shellPojo.getParams().get(key);
            if (value == null) {
                throw ShellException.valueOf("{1} placeholder:{2} did not get input parameters,params:{3}", ShellConstant.CMD.INPUT, key, shellPojo.getParams().toString());
            }
            inputPojo.setValue(value);
        } else {
            inputPojo.setValue(key);
        }
        return executeCmd(CmdTypeEnum.INPUT, robot, inputPojo);
    }


}
