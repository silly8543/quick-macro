package com.github.silly8543.reptile.shell.processor.cmd;


import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.ClickTypeEnum;
import com.github.silly8543.reptile.instruction.enums.CmdTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.ClickPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * 点击脚本
 * click -l  单击
 * click -r  右键
 * click -d  双击
 * Created by silly on 2019/3/12 12:53
 */
@Script(ShellConstant.CMD.CLICK)
public class ClickScript implements IScript<ClickPojo> {

    @Override
    public ClickPojo parser(String script, List<String> params) throws ShellException {
        String type = params.get(0);
        ClickTypeEnum typeEnum = ClickTypeEnum.getTypeEnum(type);
        if (typeEnum == null) {
            throw exParamTypeNotSupported(ShellConstant.CMD.CLICK, script, params);
        }
        ClickPojo clickPojo = new ClickPojo();
        clickPojo.setType(typeEnum);
        switch (typeEnum) {
            //拖拽
            case DRAG_DROP:
                try {
                    int x = Integer.parseInt(params.get(1));
                    clickPojo.setX(x);
                } catch (Exception ex) {
                    throw ShellException.valueOf(ex, "{1} -dd x coordinate conversion failed,ex:{2},script:{3}", ShellConstant.CMD.CLICK, ex.toString(), getScript(script, params));
                }
                try {
                    int y = Integer.parseInt(params.get(2));
                    clickPojo.setY(y);
                } catch (Exception ex) {
                    throw ShellException.valueOf(ex, "{1} -dd y coordinate conversion failed,ex:{2},script:{3}", ShellConstant.CMD.CLICK, ex.toString(), getScript(script, params));
                }
                break;
            case ROLLING_WHEEL:
                try {
                    int x = Integer.parseInt(params.get(1));
                    clickPojo.setX(x);
                } catch (Exception ex) {
                    throw ShellException.valueOf(ex, "{1} -dr wheelAmt conversion failed,ex{2},script:{3}", ShellConstant.CMD.CLICK, ex.toString(), getScript(script, params));
                }
                break;
        }
        return clickPojo;
    }

    @Override
    public Map<String, String> execute(Robot robot, ClickPojo clickPojo, ShellPojo shellPojo) throws ShellException {
        return executeCmd(CmdTypeEnum.CLICK, robot, clickPojo);
    }
}
