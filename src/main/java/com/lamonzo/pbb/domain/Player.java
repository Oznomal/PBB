package com.lamonzo.pbb.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Player {

    //== FIELDS ==
    private String name;
    private Position position;
    private String team;
    private String htmlIdentifier;

    //Needs a one to many relation ship, one player will have many stats
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
