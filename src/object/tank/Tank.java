package object.tank;

import object.bullet.Bullet;
import object.bullet.OpposingBullet;
import object.bullet.OwnBullet;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 此类作为OpposingTank和OwnTank的父类
 * 实现Runnabel接口，使得其子类可以创建线程
 *
 * @author 陶敏帆
 * @version 1.0
 */
public abstract class Tank implements TankWay, Runnable {
    //坦克左上角的横坐标
    private int x;
    //坦克左上角的纵坐标
    private int y;
    //坦克的朝向（0->朝上, 1->朝下, 2->朝左, 3->朝右）默认为0（朝上）
    private int direction = 0;
    //坦克的颜色
    protected Color color;
    //坦克的生命值
    private int health = 1;
    //坦克的速度
    public static final int speed = 5;

    public Tank() {
    }

    //构造器（初始化x,y的值）
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    //发射子弹
    public void shot(int type) {
        //临时变量p,用来临时保存Bullet_01对象
        Bullet p = null;
        if (type == 0)
        switch (getDirection()) {
            case 0: //子弹朝上
                Bullet.bullets.add(p = new OwnBullet(getX() + 20, getY(), getDirection()));
                break;
            case 1: //子弹朝下
                Bullet.bullets.add(p = new OwnBullet(getX() + 20, getY() + 60, getDirection()));
                break;
            case 2: //子弹朝左
                Bullet.bullets.add(p = new OwnBullet(getX(), getY() + 20, getDirection()));
                break;
            case 3: //子弹朝右
                Bullet.bullets.add(p = new OwnBullet(getX() + 60, getY() + 20, getDirection()));
                break;
        }
        else
            switch (getDirection()) {
                case 0: //子弹朝上
                    Bullet.bullets.add(p = new OpposingBullet(getX() + 20, getY(), getDirection()));
                    break;
                case 1: //子弹朝下
                    Bullet.bullets.add(p = new OpposingBullet(getX() + 20, getY() + 60, getDirection()));
                    break;
                case 2: //子弹朝左
                    Bullet.bullets.add(p = new OpposingBullet(getX(), getY() + 20, getDirection()));
                    break;
                case 3: //子弹朝右
                    Bullet.bullets.add(p = new OpposingBullet(getX() + 60, getY() + 20, getDirection()));
                    break;
            }
        new Thread(p).start();
    }

    //绘制坦克
    @Override
    public void drawTank(int x, int y, int direction, Graphics graphics) {
        switch (direction) {
            case 0: //朝上
                //画出坦克的左轮子
                graphics.fill3DRect(x, y, 10, 60, false);
                //画出坦克的右轮子
                graphics.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克的身体
                graphics.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出坦克的圆盖
                graphics.fillOval(x + 10, y + 20, 20, 20);
                //画出炮筒
                graphics.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1: //朝下
                //画出坦克的左轮子
                graphics.fill3DRect(x, y, 10, 60, false);
                //画出坦克的右轮子
                graphics.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克的身体
                graphics.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出坦克的圆盖
                graphics.fillOval(x + 10, y + 20, 20, 20);
                //画出炮筒
                graphics.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 2: //朝左
                //画出坦克的上轮子
                graphics.fill3DRect(x, y, 60, 10, false);
                //画出坦克的下轮子
                graphics.fill3DRect(x, y + 30, 60, 10, false);
                //画出坦克的身体
                graphics.fill3DRect(x + 10, y + 10, 40, 20, false);
                //画出坦克的圆盖
                graphics.fillOval(x + 20, y + 10, 20, 20);
                //画出炮筒
                graphics.drawLine(x + 30, y + 20, x, y + 20);
                break;
            case 3: //朝右
                //画出坦克的上轮子
                graphics.fill3DRect(x, y, 60, 10, false);
                //画出坦克的下轮子
                graphics.fill3DRect(x, y + 30, 60, 10, false);
                //画出坦克的身体
                graphics.fill3DRect(x + 10, y + 10, 40, 20, false);
                //画出坦克的圆盖
                graphics.fillOval(x + 20, y + 10, 20, 20);
                //画出炮筒
                graphics.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
        }
    }


}
