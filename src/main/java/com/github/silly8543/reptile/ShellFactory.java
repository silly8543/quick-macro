package com.github.silly8543.reptile;

import com.github.silly8543.reptile.common.ShellConstant;
import com.github.silly8543.reptile.common.annotations.Script;
import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.scan.ClasspathPackageScanner;
import com.github.silly8543.reptile.common.scan.PackageScanner;
import com.github.silly8543.reptile.common.utils.ShellUtils;
import com.github.silly8543.reptile.common.utils.StringUtils;
import com.github.silly8543.reptile.shell.actuator.ActuatorParser;
import com.github.silly8543.reptile.shell.actuator.ShellExecute;
import com.github.silly8543.reptile.shell.actuator.ShellParser;
import com.github.silly8543.reptile.shell.actuator.TempletExecute;
import com.github.silly8543.reptile.shell.actuator.pojo.ActuatorParserPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.TemplatePojo;
import com.github.silly8543.reptile.shell.processor.IScript;

import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.*;

/**
 * shell工厂
 *
 * @Author: silly
 * @Date: 2019/3/21 11:59
 * @Version 1.0
 * @Desc 用于初始化各种对象
 */
public class ShellFactory {
    //操作上下文
    private static ShellFactory context;
    private Robot robot;
    private Map<String, IScript> beansScripts;
    private Map<String, ShellPojo> shellPojos;
    private Map<String, TemplatePojo> templatePojos;

    /**
     * 构造函数
     */
    private ShellFactory() {
        shellPojos = new HashMap<>();
        beansScripts = new HashMap<>();
        templatePojos = new HashMap<>();
    }

    /**
     * 获取上下操作文
     *
     * @return
     */
    public static ShellFactory getContext() {
        if (context == null) {
            context = new ShellFactory();
        }
        return context;
    }

    /**
     * 执行:无输入参数
     *
     * @param name  执行脚本的名称
     * @param datas 脚本行数据
     * @return
     */
    public ShellPojo executeScript(String name, List<String> datas) throws ShellException {
        if (StringUtils.isBlank(name)) {
            throw ShellException.valueOf("name is empty");
        }
        if (datas == null || datas.size() <= 0) {
            throw ShellException.valueOf("data is empty");
        }
        ArrayList<String[]> newDatas = new ArrayList<>();
        for (String str : datas) {
            //空行跳过
            if (StringUtils.isBlank(str)) {
                continue;
            }
            //标记字符，跳过
            if (ShellUtils.isMarkTag(str)) {
                continue;
            }
            newDatas.add(StringUtils.spilt(str, ShellUtils.SCRIPT_SEPARATOR));
        }
        ShellParser shellParser = new ShellParser(name, newDatas);
        ShellPojo shellPojo = shellParser.parse();
        return execute(shellPojo);
    }

    public ShellPojo execute(String name, String... inputParams) throws ShellException {
        ArrayList<String> params = new ArrayList<>();
        if (inputParams != null && inputParams.length > 0) {
            for (String param : inputParams) {
                params.add(param);
            }
        }
        return execute(name, params);
    }

    /**
     * 执行：从扫描中的缓存获取shell pojo执行
     *
     * @param name
     * @param inputParams
     * @return
     */
    public ShellPojo execute(String name, ArrayList<String> inputParams) throws ShellException {
        ShellPojo shellPojo = shellPojos.get(name);
        if (shellPojo == null) {
            throw ShellException.valueOf("shell pojo not found,name={1}", name);
        }
        return execute(shellPojo, inputParams);
    }

    public ShellPojo execute(ShellPojo shellPojo, ArrayList<String> inputParams) throws ShellException {
        int i = 0;
        LinkedHashMap<String, String> params = shellPojo.getParams();
        if (params != null && params.size() >= 0 && inputParams != null && inputParams.size() >= 0) {
            //输入参数个数与脚本参数是否一样
            if (inputParams.size() != params.size()) {
                throw ShellException.valueOf("shell input params size({1}) not equal shell params({2}) size", inputParams.size() + "", params.size() + "");
            }
            //构造参数
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                entry.setValue(inputParams.get(i));
                i++;
            }
        }
        return execute(shellPojo);
    }

    /**
     * 执行：需要指定shellpojo
     *
     * @param shellPojo 需要执行的shell pojo
     * @return shellPojo 执行完成的shell pojo
     * @throws ShellException
     */
    public ShellPojo execute(ShellPojo shellPojo) throws ShellException {
        ShellExecute shellExecute = new ShellExecute(shellPojo);
        return shellExecute.execute();
    }

    /**
     * 模板转shell pojo
     *
     * @param shellName    shell名
     * @param templateName 模板名
     * @param moduleParam  模块参数
     * @return
     */
    public ShellPojo templateToShellPojo(String shellName, String templateName, String moduleParam) throws ShellException {
        TemplatePojo templatePojo = templatePojos.get(templateName);
        if (templatePojo == null) {
            throw ShellException.valueOf("template pojo is not found,template name:{0}", templateName);
        }
        TempletExecute execute = new TempletExecute(shellName, templatePojo, moduleParam);
        return execute.execute();
    }

    /**
     * 初始化shell
     *
     * @param datas shell脚本数据
     * @throws ShellException
     */
    public void initShell(List<String> datas) throws ShellException {
        ActuatorParserPojo parserPojo = new ActuatorParser(datas).execute();
        switch (parserPojo.getType()) {
            case ShellConstant.MARK_TYPE.TEMPLATE:
                addTemplatePojo(parserPojo.getTemplatePojo());
                break;
            default:
                addShellPojo(parserPojo.getShellPojo());
        }
    }

    /**
     * 初始化
     *
     * @param robot
     * @throws Exception
     */
    public void init(Robot robot) throws Exception {
        this.robot = robot;
        initScript();
    }

    public Robot getRobot() {
        return robot;
    }

    /**
     * 获取脚本执行器
     *
     * @param script
     * @return
     */
    public IScript getScript(String script) throws ShellException {
        if (script == null) {
            return null;
        }
        IScript bean = beansScripts.get(script);
        if (bean == null) {
            throw ShellException.valueOf("Script not supported:" + script, null);
        }
        return bean;
    }

    /**
     * 添加shell实体对象
     *
     * @param shellPojo
     */
    public void addShellPojo(ShellPojo shellPojo) {
        shellPojos.put(shellPojo.getName(), shellPojo);
    }

    /**
     * 添加template实体对象
     *
     * @param templatePojo
     */
    public void addTemplatePojo(TemplatePojo templatePojo) {
        templatePojos.put(templatePojo.getName(), templatePojo);
    }

    /**
     * 获取shell实体对象
     *
     * @param shellName shell名称
     * @return
     */
    public ShellPojo getShellPojo(String shellName) {
        if (StringUtils.isBlank(shellName)) {
            return null;
        }
        ShellPojo shellPojo = shellPojos.get(shellName);
        if (shellPojo == null) {
            return null;
        }
        return shellPojo.copy();
    }

    /**
     * 获取template实体对象
     *
     * @param templateName template名称
     * @return
     */
    public TemplatePojo getTemplatePojo(String templateName) {
        if (StringUtils.isBlank(templateName)) {
            return null;
        }
        TemplatePojo templatePojo = templatePojos.get(templateName);
        if (templatePojo == null) {
            return null;
        }
        return templatePojo;
    }

    /**
     * 扫描script
     */
    private void initScript() throws Exception {
        String basePackage = "com.github.silly8543.reptile.shell.processor";
        PackageScanner packageScanner = new ClasspathPackageScanner(basePackage);
        List<String> list = packageScanner.getFullyQualifiedClassNameList();
        if (list != null && list.size() > 0) {
            for (String name : list) {
                Class clazz = Class.forName(name);
                Annotation annotation = clazz.getAnnotation(Script.class);
                if (annotation != null) {
                    Script cmd = (Script) annotation;
                    IScript instance = (IScript) clazz.newInstance();
                    beansScripts.put(cmd.value(), instance);
                }
            }
        }
    }

}
