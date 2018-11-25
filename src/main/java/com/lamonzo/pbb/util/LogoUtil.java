package com.lamonzo.pbb.util;

import com.lamonzo.pbb.constants.TeamConstants;
import javafx.scene.image.Image;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogoUtil {

    //== FIELDS ==
    private final String LOGO_PREFIX = "/img/teams/";
    private final String LOGO_SUFFIX = "_70.png";
    private Map<String, Image> logoMap;

    //== PUBLIC METHODS ==
    @PostConstruct
    private void buildMap(){
        logoMap = new HashMap<>();
        for(String abbrv : TeamConstants.TEAM_MAP.keySet())
            logoMap.put(abbrv, new Image(getClass().getResourceAsStream(LOGO_PREFIX + abbrv + LOGO_SUFFIX)));

    }

    public Image getTeamLogo(String teamAbbrv){
        return logoMap.get(teamAbbrv);
    }
}
