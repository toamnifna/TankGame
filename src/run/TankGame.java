package run;

import paint.panel.MyPanel;
import paint.window.MyWindow;

/**
 * 此类用来开始坦克大战
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class TankGame {
    public static void main(String[] args) throws InterruptedException {
        //创建一个面板对象，其中画着所有需要的图案
        MyPanel myPanel = new MyPanel();
        //开启总面板线程，使得总面板可以重绘，以反映实时画面
        (new Thread(myPanel)).start();
        //创建一个窗口对象，将myPanel面板对象加入到这个窗口对象中
        MyWindow window = new MyWindow(myPanel);
        //window对象监听myPanel面板的键盘事件
        window.addKeyListener(myPanel);
    }
}
