package object.tank;

import java.awt.*;

/**
 * 关于坦克的方法
 *
 * @author 陶敏帆
 * @version 1.0
 */
public interface TankWay {

    /**
     * @param x         坦克左上角的横坐标
     * @param y         坦克左上角的纵坐标
     * @param direction 坦克朝向的方向
     * @param graphics  一支画笔
     *                  绘制一辆坦克
     */
    void drawTank(int x, int y, int direction, Graphics graphics);

    /**
     * 发射子弹
     */
    void shot();

    boolean tankIfOverlap(Tank tank);
}
