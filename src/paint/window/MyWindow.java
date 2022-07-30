package paint.window;


import record.Record;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Window类用来创建一个自定义窗口，功能：
 * 设置窗口的大小
 * 设置窗口是否可视化
 *
 * @author 陶敏帆
 * @version 1.0
 */
public class MyWindow extends JFrame {
    //窗口的宽度
    public static final int WIDTH = 1000;
    //窗口的高度
    public static final int HEIGHT = 1000;

    //初始化窗口
    {
        //设置窗口的大小
        this.setSize(WIDTH, HEIGHT);
        //设置窗口的是否可视化
        this.setVisible(true);
        //设置窗口点击右上角的×可以退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加一个关闭窗口事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //保存游戏记录
                try {
                    Record.keepRecord();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public MyWindow() {
    }

    //传入一个面板
    public MyWindow(JPanel panel) throws HeadlessException {
        //将面板加入到窗口中
        this.add(panel);
    }
}
