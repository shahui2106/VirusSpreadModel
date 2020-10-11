package com.vsm.zz.view;

import com.vsm.zz.bean.Ball;
import com.vsm.zz.help.Config;
import com.vsm.zz.help.MyTool;
import com.vsm.zz.help.ThreadHelp;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Zhu
 * @createtime 2020/9/30-14:01
 */
public class AnimatePanel extends JPanel {
    //计算获取此面板的宽度和高度
    int animatePanelWidth = Config.mainFrameWidth - 2 * Config.animationMarginLeft;
    int animatePanelHeight = Config.mainFrameHeight / 2;
    int animatePanelY = Config.centreMarginTop + Config.menuMarginTop + Config.animationMarginTop + Config.mainFrameHeight * 7 / 20;

    //小球初始化
//    Ball[] ball = new Ball[Config.personNum];
    ArrayList<Ball> ball = new ArrayList<Ball>();

    public AnimatePanel() {
        //设置我自由布局
        this.setLayout(null);
        //设置此面板的大小
        this.setBounds(Config.animationMarginLeft, animatePanelY, animatePanelWidth, animatePanelHeight);
        this.setBackground(new Color(200, 200, 200));

        for (int i = 0; i < Config.personNum; i++)
            ball.add(new Ball(MyTool.getRandom(), MyTool.getRandom(), Config.animateCircleRadius, 5, 7, Color.GREEN, 2, this));


        //线程池
        for (int i = 0; i < Config.personNum; i++) {
            int finalI = i;
            ThreadHelp.getThreadPool().execute(() -> {
                while (true) {
                    if (Config.isStart) {
                        ball.get(finalI).moveBall(this);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    repaint();
                }
            });
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < Config.personNum; i++) {
            if (i >= ball.size())
                ball.add(new Ball(MyTool.getRandom(), MyTool.getRandom(), Config.animateCircleRadius, 5, 7, Color.GREEN, 2, this));
            Ball myBall = ball.get(i);
            myBall.collision(ball);
            myBall.drawBall(g);
        }
/*        for (int i = 0; i < Config.personNum; i++) {
            g.setColor(Color.green);
            g.fillOval(ball[i].x, ball[i].y, ball[i].radius, ball[i].radius);
        }*/
    }

}
