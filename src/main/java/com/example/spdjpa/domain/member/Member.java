package com.example.spdjpa.domain.member;

import com.example.spdjpa.domain.team.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private String memberId;
    private String name;
    private Team team;
}
