package com.lamonzo.pbb.controller.tab;

import com.lamonzo.pbb.constants.PositionConstants;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DefensiveTackleTabController extends BaseTabController{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        positionName = PositionConstants.DT;
        buildTreeTable();
    }
}
