package object.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * 此类为对方坦克
 * 用Vector集合来保存对方坦克
 * 在代码块中，可以修改对方坦克的初始化颜色（默认为红色）
 * 在有参构造器中，可以初始化坦克的位置（x, y值）
 * number为对方坦克的数量
 * 如何实现对方坦克的独立重绘，而不是依赖按键事件去重绘？？？
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class OpposingTank extends Tank {
    //设置坦克的颜色
    {
        //坦克的颜色（红色）
        color = Color.RED;
    }

    //坦克的数量
    public static int number = 8;
    //创建一个Vector集合用来保存OpposingTank对象（考虑到是多线程，所以使用Vector集合，线程安全）
    public static Vector<OpposingTank> opposingTanks = new Vector<OpposingTank>();

    public OpposingTank() {
    }

    //确定坦克的生成位置
    public OpposingTank(int x, int y) {
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
        super.shot(1);
    }

    //判断坦克是否重叠
    @Deprecated
    @Override
    public boolean tankIfOverlap(Tank tank) {
        for (OpposingTank opposingTank : opposingTanks) {
            //只和其他坦克判断是否重叠,不跟自己判断
            if (this != opposingTank) {
                //判断当前坦克（在线程中的坦克）的方向
                switch (getDirection()) {
                    //当前坦克上下运动
                    case 0:
                    case 1:
                        //如果当前坦克继续位移的纵坐标
                        int willy = getDirection() == 0 ? this.getY() - OpposingTank.speed : this.getY() + OpposingTank.speed;
                        //判断其他坦克的方向
                        switch (opposingTank.getDirection()) {
                            //其他坦克上下运动
                            case 0:
                            case 1:
                                if ((willy >= opposingTank.getY() && willy <= opposingTank.getY() + 60
                                        && this.getX() >= opposingTank.getX() && this.getX() <= opposingTank.getX() + 40)
                                        || (willy + 60 >= opposingTank.getY() && willy + 60 <= opposingTank.getY() + 60
                                        && this.getX() >= opposingTank.getX() && this.getX() <= opposingTank.getX() + 40))
                                    return true;
                                break;
                            //其他坦克左右运动
                            case 2:
                            case 3:
                                if ((willy >= opposingTank.getY() && willy <= opposingTank.getY() + 40
                                        && this.getX() >= opposingTank.getX() && this.getX() <= opposingTank.getX() + 60
                                        || willy + 60 >= opposingTank.getY() && willy + 60 <= opposingTank.getY() + 40
                                        && this.getX() >= opposingTank.getX() && this.getX() <= opposingTank.getX() + 60))
                                    return true;
                                break;
                        }
                        //当前坦克左右运动
                    case 2:
                    case 3:
                        //如果当前坦克继续位移的横坐标
                        int willx = getDirection() == 2 ? this.getX() - OpposingTank.speed : this.getX() + OpposingTank.speed;
                        //判断其他坦克的方向
                        switch (opposingTank.getDirection()) {
                            //其他坦克上下运动
                            case 0:
                            case 1:
                                if (willx >= opposingTank.getX() && willx <= opposingTank.getX() + 40
                                        && this.getY() >= opposingTank.getY() && this.getY() <= opposingTank.getY() + 60
                                        || willx + 60 >= opposingTank.getX() && willx + 60 <= opposingTank.getX() + 40
                                        && this.getY() >= opposingTank.getY() && this.getY() <= opposingTank.getY() + 60)
                                    return true;
                                break;
                            //其他坦克左右运动
                            case 2:
                            case 3:
                                if (willx >= opposingTank.getX() && willx <= opposingTank.getX() + 60
                                        && this.getY() >= opposingTank.getY() && this.getY() <= opposingTank.getY() + 40
                                        || willx + 60 >= opposingTank.getX() && willx + 60 <= opposingTank.getX() + 60
                                        && this.getY() >= opposingTank.getY() && this.getY() <= opposingTank.getY() + 40)
                                    return true;
                                break;
                        }
                }
            }
        }
        return false;
    }

    //新建对方坦克，并将其放入到集合中
    public static void generateTank() {
        for (int i = 0; i < number; i++) {
            //将对方的坦克添加到opposingTanks集合中
            opposingTanks.add(new OpposingTank(75 * (i + 1), 0));
        }
    }

    //对方坦克随机移动并攻击
    @Override
    public void run() {
        while (true) {
            //n为对方坦克随机的一个动向
            int n = (int) (Math.random() * 4);
            //判断坦克是否将要重叠,若重叠则反方向运动,若不重叠则随机运动
            if (tankIfOverlap(this)) {
                //根据坦克的当前方向,进行反方向运动
                switch (this.getDirection()) {
                    //坦克朝上运动时
                    case 0:
                        //让坦克朝下
                        setDirection(1);
                        //增加坦克的纵坐标
                        setY(getY() + 5);
                        break;
                    //坦克朝下运动时
                    case 1:
                        //让坦克朝上
                        setDirection(0);
                        //减小坦克的纵坐标
                        setY(getY() - 5);
                        break;
                    //坦克朝左运动时
                    case 2:
                        //让坦克朝右
                        setDirection(3);
                        //增大坦克的横坐标
                        setX(getX() + 5);
                        break;
                    //坦克朝右运动时
                    case 3:
                        //让坦克朝左
                        setDirection(2);
                        //减小坦克的横坐标
                        setX(getX() - 5);
                        break;
                }
            } else {
                //让坦克随机的动起来（向下的概率更大）
                switch (n) {
                    case 0:
                        //让坦克朝上
                        setDirection(0);
                        //减小坦克纵坐标
                        setY(getY() - 5);
                        if (n == (int) (Math.random() * 2 + n)) {
                            //让坦克朝下
                            setDirection(1);
                            //增加坦克的纵坐标
                            setY(getY() + 5);
                        }
                        break;
                    //坦克朝下运动
                    case 1:
                        //让坦克朝下
                        setDirection(1);
                        //增加坦克的纵坐标
                        setY(getY() + 5);
                        break;
                    //坦克朝左运动
                    case 2:
                        //让坦克朝左
                        setDirection(2);
                        //减小坦克的横坐标
                        setX(getX() - 5);
                        if (n == (int) (Math.random() * 2 + n)) {
                            //让坦克朝下
                            setDirection(1);
                            //增加坦克的纵坐标
                            setY(getY() + 5);
                        }
                        break;
                    //坦克朝右运动
                    case 3:
                        //让坦克朝右
                        setDirection(3);
                        //增大坦克的横坐标
                        setX(getX() + 5);
                        if (n == (int) (Math.random() * 2 + n)) {
                            //让坦克朝下
                            setDirection(1);
                            //增加坦克的纵坐标
                            setY(getY() + 5);
                        }
                        break;
                }
            }
            //发射子弹
            this.shot();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.getHealth() == 0) break;

        }
    }
}
