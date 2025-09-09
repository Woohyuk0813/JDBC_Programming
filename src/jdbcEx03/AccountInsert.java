package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountInsert {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        String sql = "insert into accounts(ano,owner,balance) values(?,?,?)";

        try(
                PreparedStatement pstmt = connection.prepareStatement(sql)){

        pstmt.setString(1, "1111111111");
        pstmt.setString(2, "강우혁");
        pstmt.setInt(3, 10000000);

        int result = pstmt.executeUpdate();
        System.out.println("저장된 행의 수" + result);


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
