package object.bullet;

import object.tank.OpposingTank;
import object.tank.OwnTank;

import java.awt.*;

/**
 * 对方的子弹
 * 可以设置子弹的颜色
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class OpposingBullet extends Bullet {
    //设置子弹的颜色
    {
        color = Color.RED;
    }

    //子弹初始化
    public OpposingBullet(int x, int y, int direction) {
        super(x, y, direction);
    }

    //绘制子弹
    @Override
    public void drawBullet(int x, int y, int direction, Graphics graphics) {
        graphics.setColor(color);
        super.drawBullet(x, y, direction, graphics);
    }

    //无用方法，但因为实现了BulletWay接口必须重写
    @Override
    public boolean bulletIfHitOppsingTank(OwnBullet ownBullet, OpposingTank opposingTank) {
        return false;
    }

    //判断对方子弹是否击中己方坦克
    @Override
    public boolean bulletIfHitOwnTank(OpposingBullet opposingBullet, OwnTank ownTank) {
        //根据坦克的方向，判断子弹是否击中坦克
        switch (ownTank.getDirection()) {
            case 0:
            case 1:
                if (opposingBullet.getX() > ownTank.getX() && opposingBullet.getX() < ownTank.getX() + 40
                        && opposingBullet.getY() > ownTank.getY() && opposingBullet.getY() < ownTank.getY() + 60) {
                    return true;
                }
                break;
            case 2:
            case 3:
                if (opposingBullet.getX() > ownTank.getX() && opposingBullet.getX() < ownTank.getX() + 60
                        && opposingBullet.getY() > ownTank.getY() && opposingBullet.getY() < ownTank.getY() + 40) {
                    return true;
                }
                break;
        }
        return false;
    }

    //在线程中改变子弹的直线位移，以及判断子弹是否存在和是否击中对方坦克
    @Override
    public void run() {

        while (true) {
            //根据子弹的方向改变其直线位移
            switch (getDirection()) {
                case 0: //子弹朝上
                    setY(getY() - 1);
                    break;
                case 1: //子弹朝下
                    setY(getY() + 1);
                    break;
                case 2: //子弹朝左
                    setX(getX() - 1);
                    break;
                case 3: //子弹朝右
                    setX(getX() + 1);
                    break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果击中，若血量为零移除己方坦克，和击中己方坦克的对方子弹
            for (OwnTank ownTank : OwnTank.ownTanks) {
                if (bulletIfHitOwnTank(this, ownTank)) {
                    //移除对方子弹
                    bullets.remove(this);
                    //己方坦克血量减一
                    ownTank.setHealth(ownTank.getHealth() - 1);
                    //移除己方坦克
                    if (ownTank.getHealth() <= 0)
                        //移除己方坦克
                        OwnTank.ownTanks.remove(ownTank);

                    OwnTank.ownTanks.remove(ownTank);

                }
            }
            //如果子弹不存在，则在集合中移除
            if (this.bulletIfNotExist(getX(), getY())) {
                bullets.remove(this);
                break;
            }
        }
    }
}
