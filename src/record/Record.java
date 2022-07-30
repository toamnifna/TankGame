package record;

import java.io.IOException;

/**
 * @author 陶敏帆
 * @version 1.0
 */
public class Record {

    //要读取和保存的文件名
    public static String readFileName = "src\\record.properties";

    //读取记录
    public static void read() {
        beatOpposingTankNum.read();
    }

    //保存记录
    public static void keepRecord() throws IOException {
        beatOpposingTankNum.keepRecord();
        OpposingTankLocation.keepRecord();

    }


}
