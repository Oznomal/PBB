package com.lamonzo.pbb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Position {

    //== FIELDS ==
    private String positionName;
    private int maxVotes;

    //== CONSTRUCTORS ==
    public Position(){}
}
