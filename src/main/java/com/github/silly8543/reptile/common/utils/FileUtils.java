package com.github.silly8543.reptile.common.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件工具类
 *
 * @Author: silly
 * @Date: 2019/6/19 10:34
 * @Version 1.0
 * @Desc
 */
public class FileUtils {


    /**
     * 读取文件:jdk8
     *
     * @param filePath 文件路径
     * @return 读到的行内容
     */
    public static List<String> read(String filePath) throws IOException {
        List<String> lines;
        try {
            lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())
                    .flatMap(line -> Arrays.stream(line.split("\n")))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw ex;
        }
        return lines;
    }
}
