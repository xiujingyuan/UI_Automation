package web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 提供统一配置入口
 * Created by Merry on 8/23/16.
 */
public class Configurator {
    private static final Logger logger = LoggerFactory.getLogger(Configurator.class);
    private static String propFile = "src/main/resources/project.properties";
    private static Map<String, String> confMap = new HashMap<String, String>();

    //读取project.properties中的配置信息
    static {
        Properties props = new Properties();
        if (!new File(propFile).exists()) {
            logger.error("配置文件不存在,请检查");
            System.exit(0);
        }
        try {
            Reporter.log("读取配置文件 " + new File(propFile).getAbsolutePath());
            props.load(new InputStreamReader(new FileInputStream(propFile), "UTF-8"));
            setProps(props);
        } catch (IOException e) {
            logger.error("读取配置文件出错 " + new File(propFile).getAbsolutePath());
            e.printStackTrace();
            System.exit(0);
        }
    }

    private static void setProps(Properties props) {
        Iterator<String> keysIt = props.stringPropertyNames().iterator();
        while (keysIt.hasNext()) {
            String key = keysIt.next();
            confMap.put(key, props.getProperty(key));
        }
    }


    public static String getBrowserName() {
        return confMap.get("browser");
    }

    public static String getIEDriver() {
        return confMap.get("iedriver");
    }

    public static String getURL() {
        return confMap.get("url");
    }

    public static String getDownloadRoute() {
        return confMap.get("download_route");
    }
}
