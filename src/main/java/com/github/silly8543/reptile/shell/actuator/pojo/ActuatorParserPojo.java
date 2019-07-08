package com.github.silly8543.reptile.shell.actuator.pojo;

import lombok.Data;

/**
 * 执行解析器实体
 *
 * @Author: silly
 * @Date: 2019/6/19 15:17
 * @Version 1.0
 * @Desc
 */
@Data
public class ActuatorParserPojo {
    /**
     * 名称
     */
    String name;
    /**
     * 类型
     */
    String type;
    
    ShellPojo shellPojo;
    TemplatePojo templatePojo;

}
