package com.github.silly8543.reptile.shell.actuator.pojo;

import com.github.silly8543.reptile.common.utils.StringUtils;
import com.github.silly8543.reptile.shell.pojo.ScriptPojo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 脚本实体
 * Created by silly on 2019/3/11 16:58
 */
@Getter
@Setter
public class ShellPojo {
    /**
     * 脚本名称
     */
    private String name;
    /**
     * 脚本命令实体
     */
    private ArrayList<ScriptPojo> scriptPojos;

    /**
     * 脚本输入参数
     */
    private LinkedHashMap<String, String> params;

    /**
     * 脚本输出参数
     */
    private Map<String, String> results;

    public ShellPojo() {
        params = new LinkedHashMap<>();
        results = new HashMap<>();
        scriptPojos = new ArrayList<>();
    }

    public ShellPojo(String name) {
        this();
        this.name = name;
    }

    /**
     * copy新对象
     *
     * @return
     */
    public ShellPojo copy() {
        ShellPojo shellPojo = new ShellPojo();
        shellPojo.setName(this.name);
        shellPojo.setScriptPojos(this.scriptPojos);
        for (Map.Entry<String, String> entry : this.getResults().entrySet()) {
            shellPojo.addResults(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : this.getParams().entrySet()) {
            shellPojo.addParams(entry.getKey(), entry.getValue());
        }
        return shellPojo;
    }

    public void addScriptPojo(ScriptPojo pojo) {
        scriptPojos.add(pojo);
    }

    /**
     * 设置输出参数
     *
     * @param key
     * @param value
     */
    public void addResults(String key, String value) {
        results.put(key, value);
    }

    /**
     * 设置输入参数
     *
     * @param key
     * @param value
     */
    public void addParams(String key, String value) {
        params.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("======name======");
        sb.append(System.lineSeparator());
        sb.append(name != null ? name : "");
        sb.append(System.lineSeparator());
        sb.append("======scripts======");
        sb.append(System.lineSeparator());
        if (scriptPojos != null && scriptPojos.size() > 0) {
            for (ScriptPojo scriptPojo : scriptPojos) {
                sb.append(scriptPojo.toString());
                sb.append(System.lineSeparator());
            }
        }
        sb.append("======params======");
        sb.append(System.lineSeparator());
        String param = StringUtils.print(params);
        if (!StringUtils.isBlank(param)) {
            sb.append(param);
        }
        sb.append("======results======");
        sb.append(System.lineSeparator());
        String result = StringUtils.print(results);
        if (!StringUtils.isBlank(result)) {
            sb.append(result);
        }
        return sb.toString();
    }

}
