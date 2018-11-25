package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class Player {

    //== FIELDS ==

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String team;

    @NotNull
    @Column(nullable = false, unique = true)
    private String htmlIdentifier;

    //Multiple players will share the same position
    @ManyToOne
    @NotNull
    private Position position;

    //Each player can have many stats
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "player")
    private List<Stat> stats;

    //== CONSTRUCTOR ==
    public Player(){}

    //== PUBLIC METHODS ==

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player [name: " + name + "\tpos: " + position + "\tteam: " + team +
                "\tid: " + htmlIdentifier + "]");

        if(stats != null && !stats.isEmpty()){
            sb.append("\tStats [");
            for(int i = 0; i < stats.size(); i++){
                Stat stat = stats.get(i);
                sb.append(i < stats.size() - 1 ? stat.getType() + " = " + stat.getValue() + ", "
                        : stat.getType() + " = " + stat.getValue() + "]");
            }
        }

        return sb.toString();
    }
}
