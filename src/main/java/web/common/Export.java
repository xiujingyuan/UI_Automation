package web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/3
 * Time: 上午11:58
 * 下载
 */

public class Export {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean download(String file_name) {
        File folder = new File(Configurator.getDownloadRoute());
        File[] files = folder.listFiles();
        boolean m = false;
        for (File file : files) {
            //如果存在指定文件名，m为true
            m = file.getName().contains(file_name);
            Reporter.log("文件名是否符合要求：" + m);
            //打印绝对路径
            Reporter.log("绝对路径：" + file.getAbsolutePath());
            //打印文件名
            Reporter.log("文件名：" + file.getName());
            //清除文件
            file.delete();
        }
        //待解决问题，如果目录下存在其他文件名的文件，会返回false
        if (m == true) {
            Reporter.log("下载校验成功，当前文件下存在指定文件。\n");
            return true;
        } else {
            Reporter.log("下载校验失败，当前路径下不存在指定文件。\n");
            return false;
        }
    }
}
