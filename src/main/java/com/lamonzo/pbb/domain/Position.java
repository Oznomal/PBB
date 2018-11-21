package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Position {

    //== FIELDS ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String positionName;
    private int maxVotes;

    //== CONSTRUCTORS ==
    public Position(){}
}
