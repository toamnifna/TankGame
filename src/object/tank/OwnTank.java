package object.tank;

import object.bullet.Bullet;
import object.bullet.OwnBullet;
import paint.window.MyWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * 此类表示己方坦克
 * 在代码块中，可以修改己方坦克的初始化颜色（默认为青色）
 * 在有参构造器中，可以初始化坦克的位置（x, y值）
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class OwnTank extends Tank implements KeyListener {
    //设置坦克的颜色
    {
        //坦克的颜色（青色）
        color = Color.CYAN;
    }

    //坦克的数量
    public static int number;
    //创建一个Vector集合用来保存OwnTank对象（考虑到是多线程，所以使用Vector集合，线程安全）
    public static Vector<OwnTank> ownTanks = new Vector<OwnTank>();

    public OwnTank() {
    }

    //确定坦克的生成位置
    public OwnTank(int x, int y) {
        super(x, y);
    }

    //绘制坦克
    @Override
    public void drawTank(int x, int y, int direction, Graphics graphics) {
        //设置坦克的颜色
        graphics.setColor(color);
        super.drawTank(x, y, direction, graphics);
    }

    //发射子弹
    @Override
    public void shot() {
        super.shot(0);
    }

    @Override
    public boolean tankIfOverlap(Tank tank) {
        return false;
    }

    //输出字符
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下
    @Override
    public void keyPressed(KeyEvent e) {
        //WASD改变坦克运动轨迹
        switch (e.getKeyCode()) {
            //坦克朝上运动
            case KeyEvent.VK_W:
                //让坦克朝上
                setDirection(0);
                //减小坦克纵坐标
                setY(getY() - 5);
                break;
            //坦克朝下运动
            case KeyEvent.VK_S:
                //让坦克朝下
                setDirection(1);
                //增加坦克的纵坐标
                setY(getY() + 5);
                break;
            //坦克朝左运动
            case KeyEvent.VK_A:
                //让坦克朝左
                setDirection(2);
                //减小坦克的横坐标
                setX(getX() - 5);
                break;
            //坦克朝右运动
            case KeyEvent.VK_D:
                //让坦克朝右
                setDirection(3);
                //增大坦克的横坐标
                setX(getX() + 5);
                break;
        }
        //J将子弹放入集合中
        if (e.getKeyCode() == KeyEvent.VK_J) {
            shot();
        }
    }

    //键盘松开
    @Override
    public void keyReleased(KeyEvent e) {

    }

    //新建己方坦克，并将其放入到集合中
    public static void generateTank(int number) {
        for (int i = 0; i < number; i++) {
            //将己方的坦克添加到OwnTanks集合中
            ownTanks.add(new OwnTank(100 * (i + 1), MyWindow.HEIGHT - 100));
        }
    }

    @Override
    public void run() {

    }
}
