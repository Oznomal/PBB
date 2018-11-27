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

    @NotNull
    @Column(nullable = false)
    private String tabHtmlLink;

    //== CONSTRUCTORS ==
    public Position(){}

    public Position(String name, int maxVotes){
        this.positionName = name;
        this.maxVotes = maxVotes;
    }

    //== PUBLIC METHODS ==

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (maxVotes != position.maxVotes) return false;
        return positionName.equals(position.positionName);
    }

    @Override
    public int hashCode() {
        int result = positionName.hashCode();
        result = 31 * result + maxVotes;
        return result;
    }
}
