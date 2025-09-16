package callableStatementEx;

import util.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MemberInsert {
    static Connection conn = DBUtil.getConnection();

    public static void main(String[] args) {
        String m_userid = "blackpink";
        String m_pwd = "blackpink1234";
        String m_email = "blaßckpink@gmail.com";
        String m_hp = "010-1234-5678";
        String sql = "{CALL SP_MEMBER_INSERT(?,?,?,?,?)}";

        try (CallableStatement call = conn.prepareCall(sql)) {
            // in 파라미터 셋팅
            call.setString(1, m_userid);
            call.setString(2, m_pwd);
            call.setString(3, m_email);
            call.setString(4, m_hp);

            // out 파라미터 셋팅
            call.registerOutParameter(5, java.sql.Types.INTEGER);

            // 실행
            call.execute();

            int rtn = call.getInt(5);

            if (rtn == 100) {
                //conn.rollback();
                System.out.println("이미 가입된 사용자입니다.");
            } else {
                //conn.commit();
                System.out.println("회원 가입이 되었습니다. 감사합니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


// 1. SP_MEMBER_LIST() 프로시저를 생성 : 전체 회원들의 정보를 출력하는 기능이다.
// 2. Memberlist 클래스에서 callableStatement 방식으로 회원들의 리스트를 출력하는 기능을 구현하세요.



}

