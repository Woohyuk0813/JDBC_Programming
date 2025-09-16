package callableStatementEx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Member {
    private int m_seq;
    private String m_userid;
    private String m_pwd;
    private String m_email;
    private String m_hp;

    public Member() {}

    public Member(int m_seq, String m_userid, String m_pwd, String m_email, String m_hp) {
        this.m_seq = m_seq;
        this.m_userid = m_userid;
        this.m_pwd = m_pwd;
        this.m_email = m_email;
        this.m_hp = m_hp;
    }

}

