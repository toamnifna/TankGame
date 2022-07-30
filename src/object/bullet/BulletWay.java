package object.bullet;

import object.tank.OpposingTank;
import object.tank.OwnTank;

import java.awt.*;

/**
 * 关于子弹的方法
 *
 * @author 陶敏帆
 * @version 1.0
 */
public interface BulletWay {
    /**
     * @param x         子弹的横坐标
     * @param y         子弹的纵坐标
     * @param direction 子弹的方向
     * @param graphics  一支画笔
     *                  <p>
     *                  绘制子弹
     */
    void drawBullet(int x, int y, int direction, Graphics graphics);

    /**
     * @param x 子弹的横坐标
     * @param y 子弹的纵坐标
     * @return false
     * <p>
     * 判断子弹是不是已经不存在了（在界外）
     */
    boolean bulletIfNotExist(int x, int y);

    /**
     * @param ownBullet    己方的子弹
     * @param opposingTank 对方的坦克
     * @return false
     * <p>
     * 判断己方子弹是否击中对方坦克
     */
    boolean bulletIfHitOppsingTank(OwnBullet ownBullet, OpposingTank opposingTank);

    /**
     * @param opposingBullet 对方的子弹
     * @param ownTank        己方的坦克
     * @return false
     * <p>
     * 判断对方子弹是否击中己方坦克
     */
    boolean bulletIfHitOwnTank(OpposingBullet opposingBullet, OwnTank ownTank);
}
