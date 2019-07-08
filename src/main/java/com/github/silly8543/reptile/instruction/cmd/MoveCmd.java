package com.github.silly8543.reptile.instruction.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Cmd;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.pojo.MovePojo;

import java.awt.*;
import java.util.Map;

/**
 * 移动命令
 * Created by silly on 2019/3/11 11:07
 */
@Cmd(ShellConstant.CMD.MOVE)
public class MoveCmd implements ICmd<MovePojo> {
    @Override
    public Map<String, String> execute(Robot robot, MovePojo movePojo) throws ShellException {
        int x = movePojo.getX();
        int y = movePojo.getY();
        if (x < 0) {
            throw ShellException.valueOf("{1} x coordinate not less than 0", ShellConstant.CMD.MOVE);
        }
        if (y < 0) {
            throw ShellException.valueOf("{1} y coordinate not less than 0", ShellConstant.CMD.MOVE);
        }
        robot.mouseMove(x, y);
        robot.delay(ShellConstant.ROBOT_DELAY);
        return null;
    }
}
