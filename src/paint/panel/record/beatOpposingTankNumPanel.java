package paint.panel.record;

import object.tank.OpposingTank;

import java.awt.*;

/**
 * @author 陶敏帆
 * @version 1.0
 */
public class beatOpposingTankNumPanel {
    //绘图
    public static void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("楷体", Font.BOLD, 20));
        graphics.drawString("累计击败的对方坦克数量", 650, 20);
        //绘制对方坦克
        new OpposingTank().drawTank(650, 40, 0, graphics);
        //绘制击败的对方坦克的数量
        graphics.drawString(record.beatOpposingTankNum.beatOpposiongTankNum + "", 700, 75);
    }
}
