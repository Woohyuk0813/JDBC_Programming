package jdbcEx03;

import jdbcEx01.Person;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountUpdate {
    public static void main(String[] args) throws SQLException {

        Connection conn = DBUtil.getConnection();

        String sql = "update accounts set owner=?, balance = ? where ano=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,"david");
            pstmt.setInt(2,9000000);
            pstmt.setString(3,"1");

            int result = pstmt.executeUpdate();
            System.out.println(result + " result updated!");

            String selectSql = "select ano, owner, balance from accounts";
            ResultSet rs = pstmt.executeQuery(selectSql);

            while (rs.next()) {
                Account account = new Account();
                account.setAno(rs.getString("ano"));
                account.setOwner(rs.getString("owner"));
                account.setBalance(rs.getInt("balance"));
                System.out.println(account.getAno() + " " + account.getOwner() + " " + account.getBalance());
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
