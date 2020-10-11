package com.vsm.zz.view;

import com.vsm.zz.bean.Virus;
import com.vsm.zz.help.Config;

import javax.swing.*;
import java.awt.*;

/**
 * @author Zhu
 * @createtime 2020/9/29-16:16
 */
public class CentrePanel extends JPanel {
    private Color[] colorTable = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED};
    private int[] cycleY = new int[4];
    //计算获取此面板的宽度和高度
    private int centrePanelWidth = Config.mainFrameWidth - 2 * Config.menuMarginLeft;
    private int centrePanelHeight = Config.mainFrameHeight / 4;

    private Virus virus;
    private JLabel virusTypeLabel;
    private JLabel lurkingRateLabel;
    private JLabel lurkingTimeLabel;
    private JLabel illRateLabel;
    private JLabel illTimeLabel;


    CentrePanel() {
        //设置自定义布局
        this.setLayout(null);
        //设置此面板的大小
        this.setBounds(Config.centreMarginLeft, Config.menuMarginTop + Config.centreMarginTop + Config.mainFrameHeight / 10, centrePanelWidth, centrePanelHeight);
        this.setBackground(new Color(186, 200, 240));
        //初始化左边图例视图
        labelLeftStyle(new JLabel("健康"), 0);
        labelLeftStyle(new JLabel("潜伏期无传染"), 1);
        labelLeftStyle(new JLabel("潜伏期有传染"), 2);
        labelLeftStyle(new JLabel("发病"), 3);

    }


    public CentrePanel(Virus virus) {
        this();
        this.virus = virus;
        //初始化右边病毒属性
        labelRightStyle((virusTypeLabel = new JLabel("类型：" + virus.getType())), 0);
        labelRightStyle(lurkingRateLabel = new JLabel("潜伏期传播率：" + virus.getLurkingRate() + " %"), 1);
        labelRightStyle(lurkingTimeLabel = new JLabel("潜伏期时长：" + virus.getLurkingTime() + " 天"), 2);
        labelRightStyle(illRateLabel = new JLabel("发病传播率：" + virus.getIllRate() + " %"), 3);
        labelRightStyle(illTimeLabel = new JLabel("发病时长：" + virus.getIllTime() + " 天"), 4);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //循环绘制四个图例图形
        for (int i = 0; i < 4; i++) {
            cycleStyle(g, i);
        }
    }


    //初始化图例标签样式
    private void labelLeftStyle(JLabel label, int index) {
        int labelHeight = (centrePanelHeight - 5 * Config.leftLabelMarginTop) / 4;
        int labelMarginTop = index * (Config.leftLabelMarginTop + labelHeight) + Config.leftLabelMarginTop;
        //存储每个标签的位置
        cycleY[index] = labelMarginTop + labelHeight / 4;
        //设置图例标签的大小与位置
        label.setBounds(Config.cycleMarginX + Config.cycleMarginRadius + Config.leftLabelMarginLeft, labelMarginTop, centrePanelWidth / 2, labelHeight);
        //设置字体
        label.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        //设置前景色
        label.setForeground(Color.WHITE);
        //将此图例标签添加到此面板中
        this.add(label);
    }

    //初始化病毒属性标签
    private void labelRightStyle(JLabel label, int index) {
        int labelHeight = (centrePanelHeight - 6 * Config.leftLabelMarginTop) / 5;
        int labelMarginTop = index * (Config.leftLabelMarginTop + labelHeight) + Config.leftLabelMarginTop;
        //设置图例标签的大小与位置
        label.setBounds(centrePanelWidth / 2, labelMarginTop, centrePanelWidth / 2 - Config.rightLabelMarginTop, labelHeight);
        //设置字体
        label.setFont(new Font("宋体", Font.PLAIN, Config.fontSize));
        //设置前景色
        label.setForeground(Color.WHITE);
        //设置字体右对齐
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        //将此图例标签添加到此面板中
        this.add(label);
    }

    //初始化图例图形
    private void cycleStyle(Graphics g, int index) {
        g.setColor(colorTable[index]);
        g.fillOval(Config.cycleMarginX, cycleY[index], Config.cycleMarginRadius, Config.cycleMarginRadius);
    }

    //刷新病毒属性显示
    public void flushRightDisplay() {
        virusTypeLabel.setText("类型：" + virus.getType());
        lurkingRateLabel.setText("潜伏期传播率：" + virus.getLurkingRate());
        lurkingTimeLabel.setText("潜伏期时长：" + virus.getLurkingTime() + " 天");
        illRateLabel.setText("发病传播率：" + virus.getIllRate() + " %");
        illTimeLabel.setText("发病时长：" + virus.getIllTime() + " 天");
    }
}
