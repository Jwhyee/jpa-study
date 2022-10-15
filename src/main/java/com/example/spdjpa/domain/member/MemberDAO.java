package com.example.spdjpa.domain.member;

import java.sql.*;

public class MemberDAO {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    public Member find(String memberId) throws SQLException {
        String sql = "SELECT MEMBER_ID, NAME FROM MEMBER M WHERE MEMBER_ID = ?";
        stmt.setString(1, memberId);
        rs = stmt.executeQuery(sql);

        String findMemberId = rs.getString("MEMBER_ID");
        String findMemberName = rs.getString("NAME");

        Member member = new Member();
        member.setMemberId(findMemberId);
        member.setName(findMemberName);

        return member;
    }
}
