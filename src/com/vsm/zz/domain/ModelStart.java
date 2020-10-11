package com.vsm.zz.domain;

import com.vsm.zz.help.Config;
import com.vsm.zz.view.AnimatePanel;
import com.vsm.zz.view.CentrePanel;
import com.vsm.zz.view.MenuPanel;

import javax.swing.*;

/**
 * @author Zhu
 * @createtime 2020/9/29-14:48
 */
public class ModelStart {

    public static void main(String[] args) {
        try {
            //设置主题
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame mainFrame = new JFrame("病毒扩散模型");
        //设置为自定义布局
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);//禁止自由调整窗口
        //设置主窗口的位置以及大小
        mainFrame.setBounds(Config.mainFrameX, Config.mainFrameY, Config.mainFrameWidth, Config.mainFrameHeight);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置主窗口的关闭事件

        CentrePanel centrePanel = new CentrePanel(Config.virus);
        MenuPanel menuPanel = new MenuPanel(centrePanel);
        AnimatePanel animatePanel = new AnimatePanel();

        mainFrame.add(menuPanel);//添加菜单面板
        mainFrame.add(centrePanel);//添加中间图例说明面板
        mainFrame.add(animatePanel);//添加动画演示面板

        mainFrame.setVisible(true);//显示主窗口
    }
}
