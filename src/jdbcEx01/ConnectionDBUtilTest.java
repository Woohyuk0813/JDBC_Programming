package jdbcEx01;

import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionDBUtilTest {
    public static void main(String[] args) {
        try {
            Connection con = DBUtil.getConnection();

            // SQL 실행을 위한 Statement 객체 생성
            Statement stmt = con.createStatement();

            int result = stmt.executeUpdate("INSERT INTO person(id,name) values(1000000,'홍길동11')");

            if (result == 1) {
                System.out.println("Insert successful!");
            }

            String selectSql = "select id, name from person";

            // SELECT 실행 후 결과(ResultSet) 받기
            ResultSet rs = stmt.executeQuery(selectSql);

            // 결과 집합에서 한 행씩 꺼내기
            while(rs.next()) {
                Person person = new Person();

                // 첫 번째 컬럼(id) 값 세팅
                person.setId(rs.getInt(1));

                // 두 번째 컬럼(name) 값 세팅
                person.setName(rs.getString(2));

                // Person 객체의 toString() 결과 출력
                System.out.println(person.toString());
            }

        } catch (Exception e) {
            System.out.println("Connection established!" + e);
        }
    }
}