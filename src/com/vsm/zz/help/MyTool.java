package com.vsm.zz.help;

/**
 * @author Zhu
 * @createtime 2020/10/11-16:42
 */
public class MyTool {
    private MyTool() {
    }

    public static int getRandom(){
        return ((int)(Math.random() * (Config.mainFrameWidth -Config.mainFrameX*2))+1);
    }

}
