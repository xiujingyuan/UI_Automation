package testCases.Demo;

import org.testng.annotations.Test;
import web.common.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/9/14
 * Time: 上午10:31
 * 数据库操作demo
 */
public class OperateDatabaseTest {
    @Test(description = "查询Test", enabled = false)
    public static void select() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //select
        String sql = "select \n" +
                "acct_combined_plate_name,acct_combined_plate_plates,acct_combined_plate_name_flag \n" +
                "from acct_combined_plate \n" +
                "where acct_combined_plate_id < ?" +
                "limit 3";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        psql.setString(1, "20");
        //执行SQL，ResultSet类，用来存放获取的结果集
        ResultSet rs = psql.executeQuery();
        //展示搜索结果
        System.out.println("-----------------");
        System.out.println("执行结果如下所示:");
        System.out.println("-----------------");
        System.out.println("口径名称" + "\t" + "合并口径包含的板块" + "\t" + "口径简称");
        System.out.println("-----------------");
        String a = null;
        String b = null;
        String c = null;
        while (rs.next()) {
            //获取acct_combined_plate_name这列数据
            a = rs.getString("acct_combined_plate_name");
            //获取acct_combined_plate_plates这列数据
            b = rs.getString("acct_combined_plate_plates");
            //获取acct_combined_plate_name_flag这列数据
            c = rs.getString("acct_combined_plate_name_flag");
            //输出结果
            System.out.println(a + "\t" + b + "\t" + c);
        }
        rs.close();
        //关闭数据库连接
        JdbcUtil.close(con);
    }

    @Test(description = "新增Test", enabled = false)
    public static void add() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete
        String sql = "INSERT INTO `acct_combined_plate` (`acct_combined_plate_name`, `acct_combined_plate_plates`, `acct_combined_plate_name_flag`)\n" +
                "VALUES (?, ?, ?)";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        psql.setString(1, "test12345");
        psql.setString(2, "15");
        psql.setString(3, "TEST2190");
        //执行SQL
        psql.executeUpdate();
        //关闭数据库连接
        JdbcUtil.close(con);
    }


    @Test(description = "更新Test", enabled = false)
    public static void update() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete
        String sql = "update acct_combined_plate set acct_combined_plate_name = ? where acct_combined_plate_name = ?";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        psql.setString(1, "test1234");
        psql.setString(2, "test12345");
        //执行SQL
        psql.executeUpdate();
        //关闭数据库连接
        JdbcUtil.close(con);
    }

    @Test(description = "删除Test", enabled = true)
    public static void delete() throws SQLException {
        //获取数据库连接
        Connection con = JdbcUtil.getconnection();
        //通过拼接构建SQL语句
        //delete
        String sql = "delete from acct_combined_plate where acct_combined_plate_name = ? and acct_combined_plate_name_flag = ? limit 1";
        PreparedStatement psql;
        //预处理
        psql = con.prepareStatement(sql);
        psql.setString(1, "test1234");
        psql.setString(2, "TEST2190");
        //执行SQL
        psql.executeUpdate();
        //关闭数据库连接
        JdbcUtil.close(con);
    }
}
