package com.lamonzo.pbb.controller.tab;

import com.lamonzo.pbb.constants.PositionConstants;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class TightEndTabController extends BaseTabController {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        positionName = PositionConstants.TE;
        buildTreeTable();
    }
}
