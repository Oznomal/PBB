package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Stat {

    //== FIELDS ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private double value;

    @ManyToOne
    @NotNull
    private StatType type;

    @ManyToOne
    @JoinColumn(name = "player_id", updatable = false, nullable = false)
    private Player player;


    //== CONSTRUCTOR ==
    public Stat (){}
}
