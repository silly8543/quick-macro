package com.github.silly8543.reptile;

import com.github.silly8543.reptile.common.exceptions.ShellException;
import com.github.silly8543.reptile.common.utils.FileUtils;
import com.github.silly8543.reptile.shell.actuator.pojo.ShellPojo;
import com.github.silly8543.reptile.shell.actuator.pojo.TemplatePojo;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/6/21 10:38
 * @Version 1.0
 * @Desc
 */
public class ShellFactoryTest {

    String filePathTemplate = "shell/cbss用户综合资料查询模板.qm";
    String filePathShell = "shell/cbss_套餐查询.qm";

    String shellName = "cbss_套餐查询";
    String templateName = "cbss用户综合资料查询模板";


    String phone = "xxxx";

    String moduleParam = "用户信息:当前产品,主体服务状态|账户信息:账户名称|优惠信息";

    @Before
    public void init() throws Exception {
        ShellFactory.getContext().init(new Robot());
        initShell();
    }

    public void initShell() throws IOException, ShellException {
        List<String> linesShell = FileUtils.read(filePathShell);
        ShellFactory.getContext().initShell(linesShell);

        List<String> linesTemplate = FileUtils.read(filePathTemplate);
        ShellFactory.getContext().initShell(linesTemplate);
    }

    @Test
    public void execute() throws ShellException {
        ArrayList<String> params = new ArrayList<>();
        params.add(phone);
        ShellPojo shellPojo = ShellFactory.getContext().execute(shellName, params);
        System.out.println(shellPojo.toString());
    }

    @Test
    public void execute1() throws ShellException {
        ShellPojo shellPojo = ShellFactory.getContext().templateToShellPojo("cbss用户综合资料查询1", "cbss用户综合资料查询模板", moduleParam);
        ArrayList<String> params = new ArrayList<>();
        params.add(phone);
        shellPojo = ShellFactory.getContext().execute(shellPojo, params);
        System.out.println(shellPojo.toString());
    }

    @Test
    public void templateToShellPojo() throws ShellException {
        ShellPojo shellPojo = ShellFactory.getContext().templateToShellPojo("cbss用户综合资料查询1", "cbss用户综合资料查询模板", moduleParam);
        System.out.println(shellPojo);
    }

    @Test
    public void getShellPojo() {
        ShellPojo shellPojo = ShellFactory.getContext().getShellPojo(shellName);
        System.out.println(shellPojo.toString());
    }

    @Test
    public void getTemplatePojo() {
        TemplatePojo templatePojo = ShellFactory.getContext().getTemplatePojo(templateName);
        System.out.println(templatePojo.toString());
    }
}