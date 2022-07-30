package record;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 陶敏帆
 * @version 1.0
 * <p>
 * 记录击败对方坦克的数量
 */
public class beatOpposingTankNum extends Record {
    //击败的对方坦克的数量
    public static int beatOpposiongTankNum = 0;
    //此纪录的名字
    public static final String s = "beatOpposingTankNum";

    //读取记录
    public static void read() {
        int n = 0;
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(Record.readFileName));
            n = Integer.parseInt(properties.getProperty(s));
            beatOpposiongTankNum = n;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //保存记录
    public static void keepRecord() {
        try {
            Properties properties = new Properties();
            properties.setProperty(s, beatOpposiongTankNum + "");
            properties.store(new FileWriter(Record.readFileName), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
