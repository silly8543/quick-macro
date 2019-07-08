package com.github.silly8543.reptile.instruction.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Cmd;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.utils.RobotUtils;
import com.github.silly8543.reptile.instruction.enums.ClearTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.ClearPojo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * 清空
 */
@Cmd(ShellConstant.CMD.CLEAR)
public class ClearCmd implements ICmd<ClearPojo> {
    @Override
    public Map<String, String> execute(Robot robot, ClearPojo clearPojo) throws ShellException {
        ClearTypeEnum type = clearPojo.getType();
        switch (type) {
            //全选清空
            case ALL:
                all(robot);
                break;
            //按键清空
            case INPUT:
                input(robot, clearPojo.getCount());
                break;
            default:
                throw ShellException.valueOf("{1} Instruction not support,type:{2}",ShellConstant.CMD.CLEAR, type.getValue());
        }
        return null;
    }

    private void all(Robot robot) {
        RobotUtils.ctrlA(robot);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

    private void input(Robot robot, int count) {
        for (int i = 0; i < count; i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.delay(ShellConstant.ROBOT_DELAY);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            robot.delay(ShellConstant.ROBOT_DELAY);
        }

    }

}
