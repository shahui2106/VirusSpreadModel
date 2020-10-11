package com.vsm.zz.bean;

import com.vsm.zz.view.AnimatePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Ball{

    /**
     * 声明小球的各种变量。
     */
    private int xPos, yPos, size, xSpeed, ySpeed,mass;
    private Color color;
    private AnimatePanel bf;

    /**
     * 球类的构造函数。
     * @param xPos 小球在X轴上的位置。
     * @param yPos 小球在Y轴上的位置。
     * @param size 小球的直径长度。
     * @param xSpeed 小球在X轴上的分速度。
     * @param ySpeed 小球在Y轴上的分速度。
     * @param color 小球的颜色。
     * @param mass 小球的质量。
     * @param bf 当前小球所在的画板。
     */
    public Ball(int xPos, int yPos, int size, int xSpeed, int ySpeed, Color color, int mass, AnimatePanel bf) {
        super();
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.color = color;
        this.mass = mass;
        this.bf = bf;
    }

    /**
     * 在画板上绘制小球。
     * @param g 当前小球。
     */
    public void drawBall(Graphics g) {
        if(xPos + size> bf.getWidth() - 4) xPos = bf.getWidth() - size - 4;
        else if(xPos < 4) xPos = 4;
        if(yPos < 4) yPos = 4;
        else if(yPos > bf.getHeight()) yPos = bf.getHeight() - size - 4;
        g.setColor(color);
        g.fillOval(xPos, yPos, size, size);
    }

    /**
     * 该方法是用来判断下一秒小球的移动方向并计算当前小球的位置。
     * @param bf 当前小球所在的画板。
     */
    public void moveBall(AnimatePanel bf) {
        if (xPos + size + xSpeed > bf.getWidth() - 4 || xPos + xSpeed < 4)
        {
            xSpeed = -xSpeed;
        }
        if (yPos + ySpeed < 2 || yPos + size + ySpeed > bf.getHeight() -4)
        {
            ySpeed = - ySpeed;
        }
        xPos += xSpeed;
        yPos += ySpeed;
    }

    /**
     * 该方法是用于判断碰撞是否发生了，如果发生了，尽量避免小球形状之间的重叠。
     * @param balls 所有小球。
     */
    public void collision(ArrayList<Ball> balls) {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (ball != this) {
                double d1 = Math.abs(this.xPos - ball.xPos);
                double d2 = Math.abs(this.yPos - ball.yPos);
                double d3 = Math.sqrt(Math.pow(d1,2) + Math.pow(d2,2));
                if (d3 <= (this.size / 2 + ball.size / 2)) {
                    if (this.xPos > ball.xPos) {
                        xPos++;
                        while(Math.sqrt(Math.pow(this.xPos - ball.xPos,2) + Math.pow(d2,2)) < this.size / 2 + ball.size / 2) xPos++;
                    } else {
                        ball.xPos++;
                        while(Math.sqrt(Math.pow(ball.xPos - this.xPos,2) + Math.pow(d2,2)) < this.size / 2 + ball.size / 2) ball.xPos++;
                    }

                    /* 应用完美弹性碰撞的速度公式 */
                    this.xSpeed=((this.mass - ball.mass) * this.xSpeed + 2 * ball.mass * ball.xSpeed)/(this.mass + ball.mass);
                    this.ySpeed=((this.mass - ball.mass) * this.ySpeed + 2 * ball.mass * ball.ySpeed)/(this.mass + ball.mass);
                    ball.xSpeed=((ball.mass - this.mass) * ball.xSpeed + 2 * this.mass * this.xSpeed)/(this.mass + ball.mass);
                    ball.ySpeed=((ball.mass - this.mass) * ball.ySpeed + 2 * this.mass * this.ySpeed)/(this.mass + ball.mass);
                }
            }
        }

    }

}