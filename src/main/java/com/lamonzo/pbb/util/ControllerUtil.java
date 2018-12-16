package com.lamonzo.pbb.util;

import com.lamonzo.pbb.controller.tab.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to get all of the instances of the position controllers to call when
 * a user clicks the 'Update Player Data' button and then the table of players for each position
 * tab need to be rebuilt.
 */
@Component
@Lazy
public class ControllerUtil {

    //================================================================================================================//
    //== FIELDS ==
    private final CenterTabController centerTabController;
    private final CornerBackTabController cornerBackTabController;
    private final DefensiveEndTabController defensiveEndTabController;
    private final DefensiveTackleTabController defensiveTackleTabController;
    private final FreeSafetyTabController freeSafetyTabController;
    private final FullBackTabController fullBackTabController;
    private final GuardTabController guardTabController;
    private final InsideLinebackerTabController insideLinebackerTabController;
    private final KickerTabController kickerTabController;
    private final OutsideLinebackerTabController outsideLinebackerTabController;
    private final PunterTabController punterTabController;
    private final QuarterBackTabController quarterBackTabController;
    private final ReturnSpecialistTabController returnSpecialistTabController;
    private final RunningBackTabController runningBackTabController;
    private final SpecialTeamerTabController specialTeamerTabController;
    private final StrongSafetyTabController strongSafetyTabController;
    private final TackleTabController tackleTabController;
    private final TightEndTabController tightEndTabController;
    private final WideReceiverTabController wideReceiverTabController;

    @Getter
    private List<BaseTabController> controllers;

    //================================================================================================================//
    //== CONSTRUCTORS ==
    @Autowired
    public ControllerUtil(CenterTabController centerTabController, CornerBackTabController cornerBackTabController,
                          DefensiveEndTabController defensiveEndTabController,
                          DefensiveTackleTabController defensiveTackleTabController,
                          FreeSafetyTabController freeSafetyTabController, FullBackTabController fullBackTabController,
                          GuardTabController guardTabController,
                          InsideLinebackerTabController insideLinebackerTabController,
                          KickerTabController kickerTabController,
                          OutsideLinebackerTabController outsideLinebackerTabController,
                          PunterTabController punterTabController, QuarterBackTabController quarterBackTabController,
                          ReturnSpecialistTabController returnSpecialistTabController,
                          RunningBackTabController runningBackTabController,
                          SpecialTeamerTabController specialTeamerTabController,
                          StrongSafetyTabController strongSafetyTabController, TackleTabController tackleTabController,
                          TightEndTabController tightEndTabController,
                          WideReceiverTabController wideReceiverTabController) {
        this.centerTabController = centerTabController;
        this.cornerBackTabController = cornerBackTabController;
        this.defensiveEndTabController = defensiveEndTabController;
        this.defensiveTackleTabController = defensiveTackleTabController;
        this.freeSafetyTabController = freeSafetyTabController;
        this.fullBackTabController = fullBackTabController;
        this.guardTabController = guardTabController;
        this.insideLinebackerTabController = insideLinebackerTabController;
        this.kickerTabController = kickerTabController;
        this.outsideLinebackerTabController = outsideLinebackerTabController;
        this.punterTabController = punterTabController;
        this.quarterBackTabController = quarterBackTabController;
        this.returnSpecialistTabController = returnSpecialistTabController;
        this.runningBackTabController = runningBackTabController;
        this.specialTeamerTabController = specialTeamerTabController;
        this.strongSafetyTabController = strongSafetyTabController;
        this.tackleTabController = tackleTabController;
        this.tightEndTabController = tightEndTabController;
        this.wideReceiverTabController = wideReceiverTabController;
    }

    //================================================================================================================//
    //== PRIVATE METHODS ==
    @PostConstruct
    private void buildList() {
        controllers = new ArrayList();
        controllers.add(centerTabController);
        controllers.add(cornerBackTabController);
        controllers.add(defensiveEndTabController);
        controllers.add(defensiveTackleTabController);
        controllers.add(freeSafetyTabController);
        controllers.add(fullBackTabController);
        controllers.add(guardTabController);
        controllers.add(insideLinebackerTabController);
        controllers.add(kickerTabController);
        controllers.add(outsideLinebackerTabController);
        controllers.add(punterTabController);
        controllers.add(quarterBackTabController);
        controllers.add(returnSpecialistTabController);
        controllers.add(runningBackTabController);
        controllers.add(specialTeamerTabController);
        controllers.add(strongSafetyTabController);
        controllers.add(tackleTabController);
        controllers.add(tightEndTabController);
        controllers.add(wideReceiverTabController);
    }
}
