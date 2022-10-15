package com.example.spdjpa.domain.member;

import java.sql.*;

public class MemberDAO {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;

    public Member find(String memberId) throws SQLException {
        String sql = "SELECT MEMBER_ID, NAME FROM MEMBER M WHERE MEMBER_ID = ?";
        pstmt.setString(1, memberId);
        rs = pstmt.executeQuery(sql);

        String findMemberId = rs.getString("MEMBER_ID");
        String findMemberName = rs.getString("NAME");

        Member member = new Member();
        member.setMemberId(findMemberId);
        member.setName(findMemberName);

        return member;
    }

    public void save(Member member) throws SQLException {
        String sql = "INSERT INTO MEMBER(MEMBER_ID, NAME) VALUES(?, ?)";
        pstmt.setString(1, member.getMemberId());
        pstmt.setString(2, member.getName());

        pstmt.executeUpdate(sql);
    }
}
