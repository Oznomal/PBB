package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @Column(nullable = false, unique = true)
    private String positionName;

    @NotNull
    @Column(nullable = false)
    private int maxVotes;

    //== CONSTRUCTORS ==
    public Position(){}

    public Position(String name, int maxVotes){
        this.positionName = name;
        this.maxVotes = maxVotes;
    }
}
