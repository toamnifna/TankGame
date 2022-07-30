package paint.panel;

import object.bullet.Bullet;
import object.bullet.OpposingBullet;
import object.bullet.OwnBullet;
import object.tank.OpposingTank;
import object.tank.OwnTank;
import paint.map.MapPanel;
import paint.panel.record.beatOpposingTankNumPanel;
import record.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



/**
 * 此类为一个总面板，将所有的绘图都放置在此面板上
 * 然后将此面板放置在窗口之上，以显示所有绘图
 * <p>
 * 因为所有的绘图都在这个总面板上
 * 且只有这个面板被调用了
 * 所以其他的类就不用再去继承JPanel类了
 * 因为即使继承了，也不会使用其面板
 * 只要用到画笔来绘画就行了
 * <p>
 * 继承了Runnable接口,来实现run方法
 * 开启总面板线程，使得总面板可以重绘，以反映实时画面
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    public MyPanel() {
        //读取记录
        Record.read();
        //批量生成对方坦克（底层为add方法）,并将这些坦克放入Vector类型集合中
        OpposingTank.generateTank();
        //为对方坦克各个创建线程
        for (OpposingTank oppTank : OpposingTank.opposingTanks) {
            //将实现了Runnable接口的类的对象传入到Thread构造器中
            Thread thread = new Thread(oppTank);
            //开启线程，并执行传入的对象的run方法
            thread.start();
        }
        //批量生成对方坦克（底层为add方法）,并将这些坦克放入Vector类型集合中
        OwnTank.generateTank(1);
    }

    //创建一个地图
    MapPanel mapPanel = new MapPanel();


    //绘图方法（用以在面板上绘图）
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制地图
        mapPanel.paint(g);
        //绘制击败对方坦克记录
        beatOpposingTankNumPanel.draw(g);
        //批量绘制对方坦克（opposingTanks为对方坦克的集合）
        for (OwnTank ownTank : OwnTank.ownTanks) {
            ownTank.drawTank(ownTank.getX(), ownTank.getY(), ownTank.getDirection(), g);
        }
        //批量绘制对方坦克（opposingTanks为对方坦克的集合）
        for (OpposingTank oppTank : OpposingTank.opposingTanks) {
            oppTank.drawTank(oppTank.getX(), oppTank.getY(), oppTank.getDirection(), g);
        }
        //绘制己方和对方的子弹
        for (Bullet bullet : Bullet.bullets) {

            if (bullet instanceof OwnBullet)
                //绘制己方的子弹
                ((OwnBullet) bullet).drawBullet(bullet.getX(), bullet.getY(), bullet.getDirection(), g);
                //绘制对方的子弹
            else
                ((OpposingBullet) bullet).drawBullet(bullet.getX(), bullet.getY(), bullet.getDirection(), g);
        }
    }

    //输出文本？
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下按键
    //只有通过按键才能重绘，如何解决？？？ 让面板成为一个线程，实时刷新画面
    @Override
    public void keyPressed(KeyEvent e) {
        //对己方坦克的面板上的按键事件做出处理
        for (OwnTank ownTank : OwnTank.ownTanks) {
            ownTank.keyPressed(e);
        }
        //对方坦克暂时不需要

    }

    //释放按键
    @Override
    public void keyReleased(KeyEvent e) {

    }

    //用来实时刷新画面
    @Override
    public void run() {
        //游戏的最大时长（秒）
        final int GameTime = 300;
        //记录刷新的次数
        int i = 0;
        //每HZ毫秒刷新一次
        final float HZ = 10f;
        while (true) {
            this.repaint();
            try {
                //睡眠时间（刷新频率）
                Thread.sleep((long) HZ);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (++i >= GameTime / (HZ / 1000)) break;
        }

    }
}
