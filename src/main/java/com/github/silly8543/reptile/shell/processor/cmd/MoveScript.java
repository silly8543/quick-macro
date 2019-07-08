package com.github.silly8543.reptile.shell.processor.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.MovePojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 移动
 * move x y
 * Created by silly on 2019/3/11 17:33
 */
@Script(value = ShellConstant.CMD.MOVE)
public class MoveScript implements IScript<MovePojo> {
    @Override
    public MovePojo parser(String script, List<String> params) throws ShellException {
        if (params.size() != 2) {
            throw ShellException.valueOf("{1} Illegal order[move x y],script:{2}", ShellConstant.CMD.MOVE, getScript(script, params));
        }
        MovePojo pojo = new MovePojo();
        try {
            pojo.setX(Integer.parseInt(params.get(0)));
        } catch (Exception ex) {
            throw ShellException.valueOf(ex,"{1} X coordinate into digital error,script:{2}",ShellConstant.CMD.MOVE,getScript(script,params));
        }
        try {
            pojo.setY(Integer.parseInt(params.get(1)));
        } catch (Exception ex) {
            throw ShellException.valueOf(ex,"{1} Y coordinate into digital error,script:{2}",ShellConstant.CMD.MOVE,getScript(script,params));
        }
        return pojo;
    }

    @Override
    public Map<String, String> execute(Robot robot, MovePojo movePojo, ShellPojo shellPojo) throws ShellException {
        return executeCmd(CmdTypeEnum.MOVE, robot, movePojo);
    }

}
