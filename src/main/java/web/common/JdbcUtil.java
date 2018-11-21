package web.common;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/13
 * Time: 下午6:59
 * JDBC连接
 */
public class JdbcUtil {
    public static Connection getconnection() {
        //声明Connection对象
        Connection con = null;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
//        String url = "jdbc:mysql://localhost:3307/capital2?useUnicode=true&characterEncoding=utf-8";
        String url = "jdbc:mysql://172.16.29.120:3306/capital?useUnicode=true&characterEncoding=utf-8";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "root";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");

        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
        return con;
    }

    /*
     * 关闭数据库连接
     */
    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
                System.out.println("数据库连接成功关闭！！");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
