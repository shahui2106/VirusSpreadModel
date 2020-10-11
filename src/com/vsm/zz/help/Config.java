package com.vsm.zz.help;

import com.vsm.zz.bean.Virus;

/**
 * @author Zhu
 * @createtime 2020/9/29-15:24
 */
public class Config {
    //病毒属性
    public static Virus virus = new Virus();
    //人数
    public static int personNum = 20;
    //动画开始控制
    public static volatile boolean isStart = false;
    public static int mainFrameWidth = 1000;
    public static int mainFrameHeight = 800;
    public static int mainFrameX = 160;
    public static int mainFrameY = 80;
    //菜单面板左边距
    public static int menuMarginLeft = 20;
    //菜单面板上边距
    public static int menuMarginTop = 20;
    //按钮左边距
    public static int buttonMarginLeft = 20;
    //按钮上边距
    public static int buttonMarginTop = 15;
    //字体大小
    public static int fontSize = 16;

    //中间面板左边距
    public static int centreMarginLeft = 20;
    //中间面板上边距
    public static int centreMarginTop = 20;
    //图例标签左边距
    public static int leftLabelMarginLeft = 15;
    //图例标签上边距
    public static int leftLabelMarginTop = 15;
    //病毒属性标签的右边距
    public static int rightLabelMarginTop = 15;
    //图例图形圆心的X
    public static int cycleMarginX = 15;
    //图例图形的半径
    public static int cycleMarginRadius = 13;

    //动画面板的左边距
    public static int animationMarginLeft = 20;
    //动画面板上边距
    public static int animationMarginTop = 20;

    //动画面板小球的半径
    public static int animateCircleRadius = 10;
}
