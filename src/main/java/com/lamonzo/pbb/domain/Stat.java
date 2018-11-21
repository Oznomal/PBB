package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Stat {

    //== FIELDS ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private double value;

    @ManyToOne
    private StatType type;

    @ManyToOne
    @JoinColumn(name = "player_id", updatable = false, nullable = false)
    private Player player;


    //== CONSTRUCTOR ==
    public Stat (){}
}
