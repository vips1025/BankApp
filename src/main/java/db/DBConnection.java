package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getInstance() {
        String url = "jdbc:mariadb://localhost:3306/cosdb";
        String username = "root";
        String password = "1234";

        // 프로토콜이 적용된 소켓
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("db connection success");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
