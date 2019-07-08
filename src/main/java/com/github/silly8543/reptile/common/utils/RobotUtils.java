package com.github.silly8543.reptile.common.utils;

import com.github.silly8543.reptile.common.ShellConstant;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * @Author: silly
 * @Date: 2019/3/20 15:34
 * @Version 1.0
 * @Desc
 */
public class RobotUtils {

    /**
     * 获取屏幕颜色
     *
     * @param x x坐标
     * @param y y坐标
     * @return
     * @throws AWTException
     */
    public static Color getScreenPixel(Robot robot, int x, int y) { // 函数返回值为颜色的RGB值。
        return robot.getPixelColor(x, y);
    }

    /**
     * 向剪贴板中添加内容
     *
     * @param content
     */
    public static void setClipbord(String content) {
        StringSelection stringSelection = new StringSelection(content);
        // 系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * 清空剪贴板
     */
    public static void clearClipbord() {
        setClipbord("");
    }

    /**
     * 从剪贴板中获取文本（粘贴）
     */
    public static String getClipboard() {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);
        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * crtl+c:复制
     *
     * @param robot
     */
    public static void ctrlC(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_C);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

    /**
     * crtl+v:粘贴
     *
     * @param robot
     */
    public static void ctrlV(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }

    /**
     * crtl+v:全选
     *
     * @param robot
     */
    public static void ctrlA(Robot robot) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.delay(ShellConstant.ROBOT_DELAY);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay(ShellConstant.ROBOT_DELAY);
    }




}
