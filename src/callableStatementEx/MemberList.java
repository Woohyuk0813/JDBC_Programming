package callableStatementEx;

import jdbc_boards.util.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberList {
    static Connection conn = DBUtil.getConnection();

    public static void main(String[] args) {
        List<Member> memberList = new ArrayList<>();

        String sql = "{CALL SP_MEMBER_LIST()}";

        try (CallableStatement call = conn.prepareCall(sql)) {
            ResultSet rs = call.executeQuery();

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
                memberList.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}