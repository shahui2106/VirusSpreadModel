package com.vsm.zz.view;

import com.vsm.zz.help.Config;
import com.vsm.zz.help.ThreadHelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 * @author Zhu
 * @createtime 2020/9/29-15:02
 */
public class MenuPanel extends JPanel {
    //开始按钮
    JButton start;
    //设置人数，即图形的个数
    JButton personNum;
    //到某个区域的概率
    JButton toPlaceRate;
    //场景切换按钮
    JButton scenes;
    //设置病毒属性按钮
    JButton virus;
    //计算获取此面板的宽度和高度
    int menuPanelWidth = Config.mainFrameWidth - 2 * Config.menuMarginLeft;
    int menuPanelHeight = Config.mainFrameHeight / 10;
    //计算按钮的宽度和高度
    int buttonWidth = (menuPanelWidth - 6 * Config.buttonMarginLeft) / 5;
    int buttonHeight = menuPanelHeight - 2 * Config.buttonMarginTop;

    boolean isVirusSettingCreate = false;
    boolean isPause = true;

    CentrePanel centrePanel;

    public void setVirusSettingCreate(boolean virusSettingCreate) {
        isVirusSettingCreate = virusSettingCreate;
    }

    //初始化按钮样式
    MenuPanel() {
        //设置自定义布局
        this.setLayout(null);
        //设置此面板的大小
        this.setBounds(Config.menuMarginLeft, Config.menuMarginTop, menuPanelWidth, menuPanelHeight);
        start = new JButton("开始");
        personNum = new JButton("人数设置");
        toPlaceRate = new JButton("某区域的概率设置");
        scenes = new JButton("场景一：学校");
        virus = new JButton("病毒属性设置");
        buttonStyle(start, 0);
        buttonStyle(personNum, 1);
        buttonStyle(toPlaceRate, 2);
        buttonStyle(scenes, 3);
        buttonStyle(virus, 4);

        menuButtonListener();
    }

    public MenuPanel(CentrePanel centrePanel) {
        this();
        this.centrePanel = centrePanel;
    }

    private void menuButtonListener() {

        //病毒属性设置
        virus.addActionListener(e -> {
            if (!isVirusSettingCreate) {
                isVirusSettingCreate = true;
                VirusSettingFrame virusSettingFrame = new VirusSettingFrame(this, centrePanel);
                virusSettingFrame.setVisible(true);
            }
        });

        //人数设置
        personNum.addActionListener(e -> {
            String num = JOptionPane.showInputDialog("请输入人数：");
            if(num == null)
                return;
            while (num.equals("") || !num.matches("\\d+")) {
                num = JOptionPane.showInputDialog("请输入人数(不可输入其他非数字字符)：");
            }

            Config.personNum = Integer.parseInt(num);
        });

        //开始按钮
        start.addActionListener(e -> {
            isPause = !isPause;
            if (isPause) {
                start.setText("开始");
                Config.isStart = false;
            } else {
                start.setText("暂停");
                Config.isStart = true;
            }
        });
    }


    //初始化按钮样式
    private void buttonStyle(JButton btn, int index) {
        //设置按钮的大小与位置
        btn.setBounds(index * (Config.buttonMarginLeft + buttonWidth) + Config.buttonMarginLeft, Config.buttonMarginTop, buttonWidth, buttonHeight);
        //设置字体
        btn.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        //将此按钮添加到此面板中
        this.add(btn);
    }

}
