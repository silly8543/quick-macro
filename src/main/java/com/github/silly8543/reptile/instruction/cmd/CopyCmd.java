package com.github.silly8543.reptile.instruction.cmd;


import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Cmd;
import com.github.silly8543.reptile.common.utils.RobotUtils;
import com.github.silly8543.reptile.instruction.pojo.CopyPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * copy命令
 */
@Cmd(ShellConstant.CMD.COPY)
public class CopyCmd implements ICmd<CopyPojo> {

    @Override
    public Map<String, String> execute(Robot robot, CopyPojo copyPojo) {
        //复制之前先清空粘贴板:防止复制到上次复制的内容
        RobotUtils.clearClipbord();
        robot.delay(ShellConstant.ROBOT_DELAY);
        //ctrl+c 复制
        RobotUtils.ctrlC(robot);
        robot.delay(ShellConstant.ROBOT_DELAY);
        //获取剪贴板内容
        String str = RobotUtils.getClipboard();
        robot.delay(ShellConstant.ROBOT_DELAY);
        //清空粘贴板
        RobotUtils.clearClipbord();
        robot.delay(ShellConstant.ROBOT_DELAY);
        //返回值
        Map<String, String> map = new HashMap<>();
        map.put(copyPojo.getPlaceholder(), str);
        return map;
    }
}
