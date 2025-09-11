package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDelete {


    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();

        String sql = "delete from accounts where ano = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "1");
            int result = pstmt.executeUpdate();
            System.out.println("삭제된 행의 수" + result);

            String selectSql = "select ano,owner,balance from accounts";
            ResultSet rs = pstmt.executeQuery(selectSql);
            while(rs.next()) {
                Account account = new Account();
                account.setAno(rs.getString(1));
                account.setOwner(rs.getString(2));
                account.setBalance(rs.getInt(3));

                System.out.println(account);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
