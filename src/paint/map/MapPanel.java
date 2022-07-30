package paint.map;

import javax.swing.*;
import java.awt.*;

//地图绘制
public class MapPanel extends JPanel {
    //此方法用来绘制地图
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //设置地图的颜色
        g.setColor(Color.BLACK);
        //设置地图的位置和大小
        g.fillRect(0, 0, 10000, 10000);
    }

}