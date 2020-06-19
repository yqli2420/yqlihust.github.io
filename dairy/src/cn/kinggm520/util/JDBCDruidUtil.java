package cn.kinggm520.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-01-16 11:19
 *  Druid数据库连接池 工具类
 */
public class JDBCDruidUtil {
    //1、定义成员变量
    private static DataSource ds;

    static{
        //加载配置文件
        Properties pro = new Properties();
        try {
            pro.load(JDBCDruidUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            try {
                ds= DruidDataSourceFactory.createDataSource(pro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取连接
    public static Connection getCon() throws SQLException {
        return ds.getConnection();
    }

    //释放资源
    public static void close(Statement stmt,Connection con){
       close(null,stmt,con);
    }

    //释放资源
    public static void close(ResultSet rs,Statement stmt, Connection con){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con!=null){
            try {
                con.close();  //归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();  //归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //获取连接池方法
    public static DataSource getDataSource(){
        return ds;
    }

}
