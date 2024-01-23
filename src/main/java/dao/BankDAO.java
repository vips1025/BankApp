package dao;

import db.DBConnection;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO - Data Access Object
 * SRP - 단일책임의 원칙
 */
public class BankDAO {
    public int deleteByNumber(int number) {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "DELETE FROM account_tb WHERE number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, number);
            int num = ps.executeUpdate();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int insert(String password, int balance) {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "INSERT INTO account_tb(password, balance, created_at) VALUES(?, ?, now())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setInt(2, balance);
            int num = ps.executeUpdate();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateByNumber(int number, int balance) {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "UPDATE account_tb SET balance = ? WHERE number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, balance);
            ps.setInt(2, number);
            int num = ps.executeUpdate();
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Account selectByNumber(int number) {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "SELECT * FROM account_tb WHERE number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, number);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Account account = new Account(
                        rs.getInt("number"),
                        rs.getString("password"),
                        rs.getInt("balance"),
                        rs.getTimestamp("created_at")
                );
                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> selectAll() {
        Connection conn = DBConnection.getInstance();
        try {
            String sql = "SELECT * FROM account_tb ORDER BY number DESC";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Account> accountLsit = new ArrayList<>();
            while (rs.next()){
                Account account = new Account(
                        rs.getInt("number"),
                        rs.getString("password"),
                        rs.getInt("balance"),
                        rs.getTimestamp("created_at")
                );
                accountLsit.add(account);
            }
            return accountLsit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
