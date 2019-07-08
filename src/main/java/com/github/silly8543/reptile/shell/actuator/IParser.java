package com.github.silly8543.reptile.shell.actuator;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.shell.pojo.logic.BlockPojo;
import com.github.silly8543.reptile.shell.pojo.logic.FieldPojo;

import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/6/18 16:55
 * @Version 1.0
 * @Desc
 */
public interface IParser<T> {
    T parse() throws ShellException;




    /**
     * 处理block代码
     *
     * @param blockPojo 块实体
     * @param datas     脚本行数据
     * @param start     起始点
     * @return 结束点
     */
    default int disposeBlock(BlockPojo blockPojo, List<String[]> datas, int start) throws ShellException {
        boolean flag = false;
        String name = blockPojo.getName();
        for (++start; start < datas.size(); start++) {
            String[] script = datas.get(start);
            String cmd = script[0];
            String param = script[1];
            if (ShellConstant.LOGIC.BLOCK.equalsIgnoreCase(cmd)) {
                if (name.equals(param)) {
                    flag = true;
                    break;
                } else {
                    //TODO
//                    throw ShellException.valueOf("Block terminator and definition are inconsistent", "start=" + name + ",end=" + param, null);
                }
            }
            switch (cmd) {
                case ShellConstant.LOGIC.FIELD:
                    FieldPojo fieldPojo = new FieldPojo(param);
                    start = disposeField(fieldPojo, datas, start);
                    blockPojo.addScript(new String[]{ShellConstant.LOGIC.FIELD, fieldPojo.getName()});
                    blockPojo.addField(fieldPojo);
                    break;
                default:
                    blockPojo.addScript(script);
                    break;
            }
        }
        if (!flag) {
            //TODO
//            throw ShellException.valueOf("Block not found end character", name, null);
        }
        return start;
    }

    /**
     * 处理字段代码
     *
     * @param fieldPojo 字段实体
     * @param datas     脚本行数据
     * @param start     起始点
     * @return 结束点
     */
    default int disposeField(FieldPojo fieldPojo, List<String[]> datas, int start) throws ShellException {
        String name = fieldPojo.getName();
        boolean flag = false;
        for (++start; start < datas.size(); start++) {
            String[] script = datas.get(start);
            String cmd = script[0];
            String param = script[1];
            if (ShellConstant.LOGIC.FIELD.equalsIgnoreCase(cmd)) {
                if (name.equals(param)) {
                    flag = true;
                    break;
                } else {
                    //TODO
//                    throw ShellException.valueOf("Field terminator and definition are inconsistent", "start=" + name + ",end=" + param, null);
                }
            } else {
                fieldPojo.addScript(script);
            }
        }
        if (!flag) {
            //TODO
//            throw ShellException.valueOf("Field not found end character", name, null);
        }
        return start;
    }

}
