package web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 提供统一入口读取输入数据
 * Created by Merry on 11/1/16.
 */
public class TestDataHelper {
    private static final Logger logger = LoggerFactory.getLogger(TestDataHelper.class);
    private static String propFile = "src/main/resources/input.properties";
    private static Map<String, String> confMap = new HashMap<String, String>();

    //读取input.properties中的配置信息
    static {
        Properties props = new Properties();
        try {
            Reporter.log("读取数据文件 " + new File(propFile).getAbsolutePath());
            props.load(new InputStreamReader(new FileInputStream(propFile), "UTF-8"));
            setProps(props);
        } catch (IOException e) {
            logger.error("读取数据文件出错 " + new File(propFile).getAbsolutePath());
            e.printStackTrace();
        }
    }

    private static void setProps(Properties props) {
        Iterator<String> keysIt = props.stringPropertyNames().iterator();
        while (keysIt.hasNext()) {
            String key = keysIt.next();
            confMap.put(key, props.getProperty(key));
        }
    }

    public static String getString(String key) {
        return confMap.get(key);
    }

    public static int getInt(String key) {
        return Integer.valueOf(confMap.get(key));
    }

    public static Iterator<String> getKeysIterator() {
        return confMap.keySet().iterator();
    }

    public Map<String, String> commonData;
    public Map<String, Map<String, String>> inputData;
    public Map<String, Map<String, String>> checkPointData;

    public static List<Map<String, String>> getInput(String testCase) {
        return null;
    }

    public static List<Map<String, String>> getCheckpoint(String testCase) {
        return null;
    }
}
