package com.vsm.zz.view;

import com.vsm.zz.bean.Virus;
import com.vsm.zz.help.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author Zhu
 * @createtime 2020/9/30-15:20
 */
public class VirusSettingFrame extends JFrame {
    private String[] labels = {"病毒类型：", "潜伏期传播率：", "潜伏期时长：", "发病传播率：", "发病时长："};
    private  Virus virus;
    private int frameWidth = 400;
    private int frameHeight = 320;
    //父对象
    private MenuPanel menuPanel;
    //中间面板
    private CentrePanel centrePanel;

    private JTextField virusTypeText;
    private MyTextField lurkingRateText;
    private MyTextField lurkingStartTimeText;
    private MyTextField lurkingEndTimeText;
    private MyTextField illRateText;
    private MyTextField illStartTimeText;
    private MyTextField illEndTimeText;
    private JButton cancel;
    private JButton ok;

    VirusSettingFrame() {
        virus = Config.virus;
        this.setLayout(null);
        this.setTitle("病毒属性设置");
        this.setBounds(Config.mainFrameX, Config.mainFrameY, frameWidth, frameHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //将此窗口置顶
        this.setAlwaysOnTop(!this.isAlwaysOnTop());

        for (int i = 0; i < 5; i++) {
            labelInit(new JLabel(labels[i]), i);
        }

        //初始化输入框
        textInit((virusTypeText = new JTextField()), 0);
        textInit((lurkingRateText = new MyTextField()), 1);
        textInit((lurkingStartTimeText = new MyTextField()), 2);
        textInit((lurkingEndTimeText = new MyTextField()), 2);
        textInit((illRateText = new MyTextField()), 3);
        textInit((illStartTimeText = new MyTextField()), 4);
        textInit((illEndTimeText = new MyTextField()), 4);

        //重新布局
        int startTimeWidth = lurkingStartTimeText.getWidth() / 2 - 5;
        int startTimeHeight = lurkingStartTimeText.getHeight();
        lurkingStartTimeText.setSize(startTimeWidth, startTimeHeight);
        illStartTimeText.setSize(startTimeWidth, startTimeHeight);
        lurkingEndTimeText.setBounds(lurkingStartTimeText.getX() + startTimeWidth + 10, lurkingStartTimeText.getY(), startTimeWidth, startTimeHeight);
        illEndTimeText.setBounds(illStartTimeText.getX() + startTimeWidth + 10, illStartTimeText.getY(), startTimeWidth, startTimeHeight);

        //初始化其他布局
        init(startTimeWidth, startTimeHeight);

    }

    public VirusSettingFrame(MenuPanel panel,CentrePanel centrePanel) {
        this();
        menuPanel = panel;
        this.centrePanel = centrePanel;
        this.addWindowListener(new WindowListenerImp());
    }

    private void init(int startTimeWidth, int startTimeHeight) {
        JLabel jLabel1 = new JLabel("-");
        jLabel1.setBounds(lurkingStartTimeText.getX() + startTimeWidth + 2, lurkingStartTimeText.getY(), 8, startTimeHeight);
        jLabel1.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        JLabel jLabel2 = new JLabel("-");
        jLabel2.setBounds(illStartTimeText.getX() + startTimeWidth + 2, illStartTimeText.getY(), 8, startTimeHeight);
        jLabel2.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        this.add(jLabel1);
        this.add(jLabel2);

        ok = new JButton("确定");
        cancel = new JButton("取消");

        cancel.setBounds(illStartTimeText.getX(), illStartTimeText.getY() + illStartTimeText.getHeight() + 20, illStartTimeText.getWidth() + 15, illStartTimeText.getHeight());
        ok.setBounds(illStartTimeText.getX() - cancel.getWidth() - 15, illStartTimeText.getY() + illStartTimeText.getHeight() + 20, illStartTimeText.getWidth() + 15, illStartTimeText.getHeight());
        this.add(ok);
        this.add(cancel);

        //取消按钮事件
        cancel.addActionListener(e -> {
            //关闭当前窗口
            dispose();
        });

        //确定按钮事件
        ok.addActionListener(e -> {
            String virusType = virusTypeText.getText().trim();
            String lurkingEndTime = lurkingEndTimeText.getText().trim();
            String lurkingStartTime = lurkingStartTimeText.getText().trim();
            String lurkingRate = lurkingRateText.getText().trim();
            String illStartTime = illStartTimeText.getText().trim();
            String illEndTime = illEndTimeText.getText().trim();
            String illRate = illRateText.getText().trim();
            if (isEmpty(virusType, lurkingEndTime, lurkingStartTime, lurkingRate, illStartTime, illEndTime, illRate))
                JOptionPane.showMessageDialog(this, "任何字段不能为空！", "提示", JOptionPane.WARNING_MESSAGE);
            else if (Integer.parseInt(lurkingStartTime) > Integer.parseInt(lurkingEndTime) || Integer.parseInt(illStartTime) > Integer.parseInt(illEndTime))
                JOptionPane.showMessageDialog(this, "结束时间不能小于开始时间！", "提示", JOptionPane.WARNING_MESSAGE);
            else {
                virus.setType(virusType);
                virus.setIllStartTime(Integer.parseInt(illStartTime));
                virus.setIllEndTime(Integer.parseInt(illEndTime));
                virus.setIllRate(Integer.parseInt(illRate));
                virus.setLurkingRate(Integer.parseInt(lurkingRate));
                virus.setLurkingEndTime(Integer.parseInt(lurkingEndTime));
                virus.setLurkingStarTime(Integer.parseInt(lurkingStartTime));
                centrePanel.flushRightDisplay();
                dispose();
            }
        });

    }

    private boolean isEmpty(String... param) {
        boolean isEmpty = false;
        for (int i = 0; i < param.length; i++) {
            isEmpty = isEmpty || param[i].equals("");
        }
        return isEmpty;
    }

    private void labelInit(JLabel label, int index) {
        int height = (frameHeight - 7 * 15) / 6;
        label.setBounds(15, index * (15 + height), frameWidth / 2 - 30, height);
        label.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(label);
    }

    private void textInit(JTextField label, int index) {
        int width = frameWidth / 2 - 45;
        int height = (frameHeight - 7 * 15) / 6;
        int x = frameWidth / 2;
        int y = index * (15 + height) + 5;
        label.setBounds(x, index * (15 + height) + 5, width, height - 10);
        label.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        if (index > 0) {
            label.setBounds(x, index * (15 + height) + 5, width - 20, height - 10);
            String text = (index & 1) != 0 ? "%" : "天";
            JLabel jLabel = new JLabel(text);
            jLabel.setBounds(x + width - 10, y, 20, height);
            jLabel.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
            this.add(jLabel);
        }
        this.add(label);
    }

    //监听此窗口的关闭等事件
    class WindowListenerImp implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            menuPanel.setVirusSettingCreate(false);
        }

        @Override
        public void windowClosed(WindowEvent e) {
            menuPanel.setVirusSettingCreate(false);
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
}
