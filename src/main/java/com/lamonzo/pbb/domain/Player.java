package com.lamonzo.pbb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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

    private String name;
    private String team;
    private String htmlIdentifier;

    //Multiple players will share the same position
    @ManyToOne
    private Position position;

    //Each player can have many stats
    @OneToMany(cascade = CascadeType.ALL)
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
