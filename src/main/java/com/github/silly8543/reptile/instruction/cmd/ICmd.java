package com.github.silly8543.reptile.instruction.cmd;

import com.github.silly8543.reptile.common.exceptions.ShellException;

import java.awt.*;
import java.util.Map;

/**
 * 命令接口
 * Created by silly on 2019/3/11 10:44
 */
public interface ICmd<R> {

    Map<String, String> execute(Robot robot, R r) throws ShellException;
}
