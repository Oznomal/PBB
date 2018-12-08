package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Settings {

    //================================================================================================================//
    //== FIELDS ==
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private int votingGoals;

    @NotNull
    @Column(nullable = false)
    private int numberOfBrowsers;

    @NotNull
    @Column(nullable = false)
    private boolean autoFill;

    @NotNull
    @Column(nullable = false)
    private boolean showBrowser;

    @NotNull
    @Column(nullable = false)
    private boolean rotateProxies;

    @NotNull
    @Column(nullable = false)
    private boolean lightningMode;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    public Settings(){}
}
