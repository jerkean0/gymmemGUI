package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class configclass {

    public Connection connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:gymsystem.db");
            return con;
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
            return null;
        }
    }

    public ResultSet getData(String query) throws SQLException {
        Connection conn = connectDB();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public boolean recordExists(String query) {
        try (ResultSet rs = getData(query)) {
            return rs.next(); 
        } catch (SQLException e) {
            return false;
        }
    }

    public int insertData(String sql, Object... values) {
        try (Connection conn = connectDB(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
            pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
            return 0;
        }
    }

    public void updateData(String sql, Object... values) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                pstmt.setObject(i + 1, values[i]);
            }
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Successfully Updated!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Update Error: " + e.getMessage());
        }
    }

    // ADDED DELETE METHOD
    public void deleteData(String sql) {
        try (Connection conn = connectDB();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Successfully Deleted!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Delete Error: " + e.getMessage());
        }
    }

    public void displayData(String sql, javax.swing.JTable table) {
        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            System.out.println("Error displaying data: " + e.getMessage());
        }
    }
}