package com.lamonzo.pbb.controller.tab;

import com.lamonzo.pbb.constants.PositionConstants;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class KickerTabController extends BaseTabController {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        buildTreeTable(PositionConstants.K);
    }
}