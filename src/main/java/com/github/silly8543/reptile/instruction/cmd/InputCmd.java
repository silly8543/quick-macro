package com.github.silly8543.reptile.instruction.cmd;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Cmd;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.utils.RobotUtils;
import com.github.silly8543.reptile.instruction.enums.InputTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.InputPojo;

import java.awt.*;
import java.util.Map;

/**
 * 输入命令
 * Created by silly on 2019/3/11 14:22
 */
@Cmd(ShellConstant.CMD.INPUT)
public class InputCmd implements ICmd<InputPojo> {

    @Override
    public Map<String, String> execute(Robot robot, InputPojo inputPojo) throws ShellException {
        InputTypeEnum type = inputPojo.getType();
        switch (type) {
            case COPY:
                copy(robot, inputPojo.getValue());
                break;
            default:
                throw ShellException.valueOf("cmd:{1} type:{2} not support", ShellConstant.CMD.INPUT, type.getValue());
        }
        return null;
    }

    /**
     * copy输入
     *
     * @param robot
     * @param value
     */
    private void copy(Robot robot, String value) {
        RobotUtils.clearClipbord();
        robot.delay(ShellConstant.ROBOT_DELAY);
        RobotUtils.setClipbord(value);
        robot.delay(ShellConstant.ROBOT_DELAY);
        RobotUtils.ctrlV(robot);
        //清空粘贴板
        RobotUtils.clearClipbord();
        robot.delay(ShellConstant.ROBOT_DELAY);
    }
}
