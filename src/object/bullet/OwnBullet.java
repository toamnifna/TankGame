package object.bullet;

import object.tank.OpposingTank;
import object.tank.OwnTank;
import record.beatOpposingTankNum;

import java.awt.*;

/**
 * 己方的子弹
 * 可以设置子弹的颜色
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class OwnBullet extends Bullet {
    //设置子弹的颜色
    {
        color = Color.GREEN;
    }

    //子弹初始化
    public OwnBullet(int x, int y, int direction) {
        super(x, y, direction);
    }

    //绘制子弹
    @Override
    public void drawBullet(int x, int y, int direction, Graphics graphics) {
        graphics.setColor(color);
        super.drawBullet(x, y, direction, graphics);
    }

    //判断己方子弹是否击中对方坦克
    @Override
    public boolean bulletIfHitOppsingTank(OwnBullet ownBullet, OpposingTank opposingTank) {
        //根据坦克的方向，判断子弹是否击中坦克
        switch (opposingTank.getDirection()) {
            case 0:
            case 1:
                if (ownBullet.getX() >= opposingTank.getX() && ownBullet.getX() <= opposingTank.getX() + 40
                        && ownBullet.getY() >= opposingTank.getY() && ownBullet.getY() <= opposingTank.getY() + 60) {
                    return true;
                }
                break;
            case 2:
            case 3:
                if (ownBullet.getX() >= opposingTank.getX() && ownBullet.getX() <= opposingTank.getX() + 60
                        && ownBullet.getY() >= opposingTank.getY() && ownBullet.getY() <= opposingTank.getY() + 40) {
                    return true;
                }
                break;
        }
        return false;
    }

    //无用方法，但因为实现了BulletWay接口必须重写
    @Override
    public boolean bulletIfHitOwnTank(OpposingBullet opposingBullet, OwnTank ownTank) {
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
            //将对方坦克集合中的每一个坦克对象拿出来，判断是否中弹
            for (OpposingTank opposingTank : OpposingTank.opposingTanks) {
                if (bulletIfHitOppsingTank(this, opposingTank)) {
                    //移除己方的子弹
                    bullets.remove(this);
                    //减一生命值
                    opposingTank.setHealth(opposingTank.getHealth() - 1);
                    if (opposingTank.getHealth() <= 0) {
                        //移除对方坦克
                        OpposingTank.opposingTanks.remove(opposingTank);
                        beatOpposingTankNum.beatOpposiongTankNum++;
                    }

                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //如果子弹不存在，则在集合中移除
            if (this.bulletIfNotExist(getX(), getY())) {
                bullets.remove(this);
                break;
            }
        }
    }
}
