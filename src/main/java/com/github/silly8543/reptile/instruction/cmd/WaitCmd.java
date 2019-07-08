package com.github.silly8543.reptile.instruction.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Cmd;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.pojo.WaitPojo;

import java.awt.*;
import java.util.Map;

/**
 * 命令:等待
 * Created by silly on 2019/3/12 13:36
 */
@Cmd(ShellConstant.CMD.WAIT)
public class WaitCmd implements ICmd<WaitPojo> {
    @Override
    public Map<String, String> execute(Robot robot, WaitPojo waitPojo) throws ShellException {
        int time = waitPojo.getTime();
        if (time <= 0) {
            throw ShellException.valueOf("{1} sleep time not less than or equal to 0s", ShellConstant.CMD.WAIT);
        }
        robot.delay(time * 1000);
        return null;
    }
}
