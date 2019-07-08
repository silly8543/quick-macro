package com.github.silly8543.reptile.shell.actuator;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.utils.StringUtils;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.TemplatePojo;
import com.github.silly8543.reptile.shell.pojo.logic.BlockPojo;
import com.github.silly8543.reptile.shell.pojo.logic.FieldPojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板执行器
 *
 * @Author: silly
 * @Date: 2019/6/20 11:18
 * @Version 1.0
 * @Desc
 */
public class TempletExecute {
    /**
     * shell脚本集合
     */
    ArrayList<String[]> shellScripts;
    /**
     * shell名称
     */
    String name;
    /**
     * 模板实体对象
     */
    TemplatePojo templatePojo;

    /**
     * 模块参数
     */
    Map<String, Map<String, String>> moduleParams;


    public TempletExecute() {
        shellScripts = new ArrayList<>();
    }

    public TempletExecute(String name, TemplatePojo templatePojo) {
        this();
        this.name = name;
        this.templatePojo = templatePojo;

    }

    /**
     * 构造函数
     *
     * @param name         shell模块名称
     * @param templatePojo 模块对象
     * @param moduleParams 模块参数
     */
    public TempletExecute(String name, TemplatePojo templatePojo, Map<String, Map<String, String>> moduleParams) {
        this(name, templatePojo);
        this.moduleParams = moduleParams;
    }


    public TempletExecute(String name, TemplatePojo templatePojo, String moduleParam) throws ShellException {
        this();
        this.name = name;
        this.templatePojo = templatePojo;
        this.moduleParams = getModuleParams(moduleParam);

    }

    public Map<String, Map<String, String>> getModuleParams(String moduleParam) throws ShellException {
        //模块参数配置样例：module1:filed1,filed2,filed3|module2:filed21,filed21|module3
        if (StringUtils.isBlank(moduleParam)) {
            throw ShellException.valueOf("module params is empty,template:{1}", name);
        }
        Map<String, Map<String, String>> moduleParams = new HashMap<>();
        String[] modules = moduleParam.split("\\|");
        for (String module : modules) {
            String[] strfields = module.split(":");
            if (strfields.length == 1) {
                moduleParams.put(strfields[0], new HashMap<>());
                continue;
            }
            String[] fields = strfields[1].split(",");
            Map<String, String> moduleField = new HashMap<>();
            for (String field : fields) {
                moduleField.put(field, field);
            }
            moduleParams.put(strfields[0], moduleField);
        }
        return moduleParams;
    }

    /**
     * 执行器
     *
     * @return
     */
    public ShellPojo execute() throws ShellException {
        //模块
        Map<String, BlockPojo> blocks = templatePojo.getBlocks();
        //脚本
        ArrayList<String[]> scripts = templatePojo.getScripts();

        if (scripts == null || scripts.size() <= 0) {
            throw ShellException.valueOf("template pojo scripts is empty");
        }
        for (String[] strs : scripts) {
            String cmd = strs[0];
            if (cmd.equalsIgnoreCase(ShellConstant.LOGIC.BLOCK)) {
                String blockName = strs[1];
                //判断是否需要配置此block
                Map<String, String> blockFilds = moduleParams.get(blockName);
                if (blockFilds == null) {
                    continue;
                }
                BlockPojo blockPojo = blocks.get(blockName);
                if (blockPojo == null) {
                    throw ShellException.valueOf("block name:{1},block pojo not found", blockName);
                }

                addBlockScripts(blockPojo, blockFilds);

            } else {
                shellScripts.add(strs);
            }
        }
        ShellParser shellParser = new ShellParser(name, shellScripts, templatePojo.getParams());
        return shellParser.parse();
    }

    /**
     * 添加blocks对应的脚本
     *
     * @param blockPojo
     * @param blockFilds
     */
    private void addBlockScripts(BlockPojo blockPojo, Map<String, String> blockFilds) throws ShellException {
        List<String[]> scripts = blockPojo.getScripts();
        Map<String, FieldPojo> fields = blockPojo.getFields();
        if (scripts == null || scripts.size() <= 0) {
            return;
        }
        //判断是否配置所有字段
        boolean flagFieldAll = (blockFilds.size() == 0) ? true : false;

        for (String[] strs : scripts) {
            String cmd = strs[0];
            if (cmd.equalsIgnoreCase(ShellConstant.LOGIC.FIELD)) {
                String fieldName = strs[1];
                FieldPojo fieldPojo = fields.get(fieldName);
                if (fieldPojo == null) {
                    throw ShellException.valueOf("field name:{1},field pojo not found", fieldName);
                }
                ArrayList<String[]> fieldScripts = fieldPojo.getScripts();
                if (fieldScripts == null || fieldScripts.size() <= 0) {
                    continue;
                }
                if (flagFieldAll) {
                    addFieldScripts(fieldScripts);
                } else {
                    //判断此字段是否需要
                    String flagField = blockFilds.get(fieldName);
                    if (StringUtils.isBlank(flagField)) {
                        continue;
                    }
                    addFieldScripts(fieldScripts);
                }
            } else {
                shellScripts.add(strs);
            }
        }
    }

    /**
     * 添加字段脚本
     *
     * @param fieldScripts
     */
    private void addFieldScripts(ArrayList<String[]> fieldScripts) {
        for (String[] field : fieldScripts) {
            shellScripts.add(field);
        }
    }


}
