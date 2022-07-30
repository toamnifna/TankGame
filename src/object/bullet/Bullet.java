package object.bullet;

import paint.window.MyWindow;

import java.awt.*;
import java.util.Vector;

/**
 * 绘制子弹
 * 有子弹线程的Run方法
 *
 * @author 陶敏帆
 * @version 1.0
 */
abstract public class Bullet implements Runnable, BulletWay {
    //子弹的横坐标
    private int x;
    //子弹的纵坐标
    private int y;
    //子弹的方向
    private int direction;
    //子弹的颜色
    protected Color color;
    //保存子弹的Vector集合
    public static Vector<Bullet> bullets = new Vector<Bullet>();
    //子弹初始化
    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    //子弹的线程（根据子弹的方向即坦克发射时的方向，进行直线位移）
    @Override
    abstract public void run();

    //绘制子弹
    @Override
    public void drawBullet(int x, int y, int direction, Graphics graphics) {
        graphics.draw3DRect(x, y, 1, 1, false);
    }

    //判断子弹是不是已经不存在了（在界外）
    @Override
    public boolean bulletIfNotExist(int x, int y) {
        if (x > MyWindow.WIDTH || x < 0 || y > MyWindow.HEIGHT || y < 0)
            return true;
        return false;
    }
}
