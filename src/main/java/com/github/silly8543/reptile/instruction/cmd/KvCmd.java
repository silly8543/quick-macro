package com.github.silly8543.reptile.instruction.cmd;


import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Cmd;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.instruction.enums.VkTypeEnum;
import com.github.silly8543.reptile.instruction.pojo.KvPojo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/4/8 11:50
 * @Version 1.0
 * @Desc
 */
@Cmd(ShellConstant.CMD.KV)
public class KvCmd implements ICmd<KvPojo> {
    @Override
    public Map<String, String> execute(Robot robot, KvPojo kvPojo) throws ShellException {
        VkTypeEnum type = kvPojo.getType();
        if (type == null) {
            throw ShellException.valueOf("{1} parameter type is empty", ShellConstant.CMD.KV);
        }
        int code1 = type.getCode1();
        int code2 = type.getCode2();
        int code3 = type.getCode3();
        if (code1 == KeyEvent.CHAR_UNDEFINED && code2 == KeyEvent.CHAR_UNDEFINED && code3 == KeyEvent.CHAR_UNDEFINED) {
            throw ShellException.valueOf("{1} parameter Invalid character", ShellConstant.CMD.KV);
        }
        return null;
    }


}
