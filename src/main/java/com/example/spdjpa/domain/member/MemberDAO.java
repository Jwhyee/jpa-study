package com.example.spdjpa.domain.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {
    Connection con;
    Statement stmt;
    ResultSet rs;

    public Member find(String memberId) throws SQLException {
        String sql = "SELECT MEMBER_ID, NAME FROM MEMBER M WHERE MEMBER_ID = ?";
        stmt = con.createStatement();
        rs = stmt.executeQuery(sql);

        String findMemberId = rs.getString("MEMBER_ID");
        String findMemberName = rs.getString("NAME");

        Member member = new Member();
        member.setMemberId(findMemberId);
        member.setName(findMemberName);

        return member;
    }
}
