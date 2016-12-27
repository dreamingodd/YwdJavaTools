package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Availability {
    public static Connection getMySqlConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 建立数据库 连接
            String url = "jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf8";
            String uid = "root";
            String pw = "";
            conn = DriverManager.getConnection(url, uid, pw);
            System.out.println("MySQL数据库连接成功!!!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getMySqlConnection();
    }
}
