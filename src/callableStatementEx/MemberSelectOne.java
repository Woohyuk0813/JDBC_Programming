package callableStatementEx;

import jdbc_boards.util.DBUtil;
import java.sql.*;

public class MemberSelectOne {
   static Connection conn = DBUtil.getConnection();

    public static void main(String[] args) throws SQLException {
        String m_userid = "blckpink";
        String sql = "{CALL SP_MEMBER_SELECT_USERID(?)}";

        try (
             CallableStatement call = conn.prepareCall(sql)) {

            call.setString(1, m_userid);
            try (ResultSet rs = call.executeQuery()) {
                while (rs.next()) {
                    Member member = new Member();
                    member.setM_seq(rs.getInt("m_seq"));
                    member.setM_userid(rs.getString("m_userid"));
                    System.out.print("Id:" + member.getM_userid() + " ");
                    member.setM_pwd(rs.getString("m_pwd"));
                    System.out.print("Password:" + member.getM_pwd() + " ");
                    member.setM_email(rs.getString("m_email"));
                    System.out.print("Email:" + member.getM_email() + " ");
                    member.setM_hp(rs.getString("m_hp"));
                    System.out.println("HP:" + member.getM_hp() + " ");
                }

            } catch (SQLException e) {
                System.err.println("회원 조회 실패: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
