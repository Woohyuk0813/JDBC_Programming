package jdbcEx01;

import java.sql.*;

public class AccountEx {
    public static void main(String[] args) {
        String DriverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/bookmarketdb?serverTimezone=Asia/Seoul";
        String username = "root";
        String password = "kang04294232@@";

        try {
            Class.forName(DriverName);
            System.out.println("Driver loaded OK!");
        } catch (Exception e) {
            System.out.println("Driver loaded failed!");
        }
        try (Connection con = DriverManager.getConnection(url, username, password)) { //도로연결
            System.out.println("AutoCommit 상태: " + con.getAutoCommit());
            con.setAutoCommit(true);

            // 매개변수화된 SQL문
            String sql = "INSERT INTO accounts(ano, owner, balance) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, "1111-1212-3232-12122");
            pstmt.setString(2, "홍길동");
            pstmt.setInt(3, 100000);

            int result = pstmt.executeUpdate();
            System.out.println("저장된 행의 수: " + result);

            String selectsql = "SELECT ano, owner, balance FROM accounts WHERE ano = ?";
            try (PreparedStatement selectpstmt = con.prepareStatement(selectsql)) {
                selectpstmt.setString(1, "1111-1212-3232-12122");
                try (ResultSet rs = selectpstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("ano = " + rs.getString("ano"));
                        System.out.println("owner = " + rs.getString("owner"));
                        System.out.println("balance = " + rs.getInt("balance"));
                    }
                }
            }
        } catch (
                Exception e) {
            System.out.println("Connection established!" + e);
        }
    }
}
