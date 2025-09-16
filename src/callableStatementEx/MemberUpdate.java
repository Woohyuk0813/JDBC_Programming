package callableStatementEx;

import jdbc_boards.util.DBUtil;
import java.sql.*;

public class MemberUpdate {
    public static void main(String[] args) {
        String m_userid = "blckpink";
        String type = "email";
        String newValue = "new_email@gmail.com";

        String sql = "{CALL SP_MEMBER_UPDATE(?, ?, ?)}";

        try (Connection conn = DBUtil.getConnection();
             CallableStatement call = conn.prepareCall(sql)) {

            call.setString(1, m_userid);
            call.setString(2, type);
            call.setString(3, newValue);

            int result = call.executeUpdate();
            if (result > 0) {
                System.out.println("회원 정보가 수정되었습니다.");
            } else {
                System.out.println("해당 아이디의 회원이 없습니다.");
            }

        } catch (SQLException e) {
            System.err.println("회원 수정 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
