package jdbcEx03;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountSearch {
    public static void main(String[] args) {
        Connection conn = DBUtil.getConnection();

        String sql = "select * from accounts";

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString("ano"));
                System.out.print(rs.getString("owner"));
                System.out.print(rs.getInt("balance"));
                System.out.println();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// 1. SP_MEMBER_LIST() 프로시저를 생성 : 전체 회원들의 정보를 출력하는 기능이다.
// 2. Memberlist 클래스에서 callableStatement 방식으로 회원들의 리스트를 출력하는 기능을 구현하세요.

