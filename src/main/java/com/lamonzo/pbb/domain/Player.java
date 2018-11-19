package com.lamonzo.pbb.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.Map;

@Getter
@Setter
public class Player {

    //== FIELDS ==
    private String name;
    private String position;
    private String team;
    private String htmlIdentifier;
    private Map<String, String> stats;

    //== CONSTRUCTOR ==
    public Player(){}

    //== PUBLIC METHODS ==

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Player [name: " + name + "\tpos: " + position + "\tteam: " + team +
                "\tid: " + htmlIdentifier + "]");

        if(stats != null){
            sb.append("\tStats [");
            Iterator<String> iterator = stats.keySet().iterator();
            while(iterator.hasNext()){
                String stat = iterator.next();
                sb.append((iterator.hasNext()) ? stat + ": " + stats.get(stat) + ", "
                        : stat + ": " + stats.get(stat) + "]");
            }
        }

        return sb.toString();
    }
}
